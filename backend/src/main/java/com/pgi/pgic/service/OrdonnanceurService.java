package com.pgi.pgic.service;

import com.pgi.pgic.dto.*;
import com.pgi.pgic.entity.*;
import com.pgi.pgic.repository.*;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdonnanceurService {

    @Autowired private ReclamationRepository reclamationRepository;
    @Autowired private InterventionRepository interventionRepository;
    @Autowired private TypeInterventionRepository typeInterventionRepository;
    @Autowired private SpecialiteMetierRepository specialiteMetierRepository;
    @Autowired private UtilisateurRepository utilisateurRepository;

    // Récupérer les réclamations nouvelles
    public List<ReclamationResponse> getReclamationsNouvelles(Specialite specialite) {
        List<Reclamation> list = reclamationRepository
                .findByStatutAndSpecialiteConcerneeOrderByUrgenteDescDateCreationAsc(
                        Reclamation.StatutReclamation.NOUVELLE, specialite);
        return list.stream().map(r -> new ReclamationResponse(
                r.getId(), r.getNumero(), r.getDescription(), r.isUrgente(),
                r.getStatut().name(), r.getDateCreation()
        )).collect(Collectors.toList());
    }

    // Qualifier une réclamation
    @Transactional
    public void qualifierReclamation(Long reclamationId, Long ordonnanceurId, ReclamationQualificationRequest request) {
        Reclamation reclamation = reclamationRepository.findById(reclamationId)
                .orElseThrow(() -> new RuntimeException("Réclamation non trouvée"));
        if (reclamation.getStatut() != Reclamation.StatutReclamation.NOUVELLE) {
            throw new RuntimeException("Réclamation déjà qualifiée ou en cours");
        }
        reclamation.setStatut(Reclamation.StatutReclamation.QUALIFIEE);
        reclamation.setUrgente(request.isUrgente());
        Ordonnanceur ordonnanceur = (Ordonnanceur) utilisateurRepository.findById(ordonnanceurId)
                .orElseThrow(() -> new RuntimeException("Ordonnanceur non trouvé"));
        reclamation.setOrdonnanceur(ordonnanceur);
        reclamationRepository.save(reclamation);
    }

    // Récupérer l'arbre des types d'intervention
    @Transactional(readOnly = true)
    public TypeArbreResponse getArbreTypes(Specialite specialite) {
        String libelle = specialite.name();
        SpecialiteMetier specialiteEntity = specialiteMetierRepository
                .findByLibelle(libelle)
                .orElseThrow(() -> new RuntimeException("Spécialité non trouvée : " + libelle));

        Hibernate.initialize(specialiteEntity.getFamilles());
        for (Famille famille : specialiteEntity.getFamilles()) {
            Hibernate.initialize(famille.getTypes());
        }

        TypeArbreResponse response = new TypeArbreResponse();
        response.setId(specialiteEntity.getId());
        response.setLibelle(specialiteEntity.getLibelle());

        List<TypeArbreResponse.FamilleArbreResponse> famillesDTO = new ArrayList<>();
        for (Famille famille : specialiteEntity.getFamilles()) {
            TypeArbreResponse.FamilleArbreResponse familleDTO = new TypeArbreResponse.FamilleArbreResponse();
            familleDTO.setId(famille.getId());
            familleDTO.setLibelle(famille.getLibelle());

            List<TypeArbreResponse.TypeArbreItemResponse> typesDTO = new ArrayList<>();
            for (TypeIntervention type : famille.getTypes()) {
                typesDTO.add(new TypeArbreResponse.TypeArbreItemResponse(
                        type.getId(), type.getLibelle(), type.getDureeEstimeeMinutes()));
            }
            familleDTO.setTypes(typesDTO);
            famillesDTO.add(familleDTO);
        }
        response.setFamilles(famillesDTO);
        return response;
    }

    // Récupérer les réclamations qualifiées sans intervention
    @Transactional(readOnly = true)
    public List<ReclamationResponse> getReclamationsQualifieesSansIntervention(Specialite specialite) {
        List<Reclamation> reclamations = reclamationRepository
                .findByStatutAndSpecialiteConcerneeAndInterventionIsNull(Reclamation.StatutReclamation.QUALIFIEE, specialite);
        return reclamations.stream()
                .map(this::toReclamationResponse)
                .collect(Collectors.toList());
    }

    private ReclamationResponse toReclamationResponse(Reclamation r) {
        return new ReclamationResponse(
                r.getId(),
                r.getNumero(),
                r.getDescription(),
                r.isUrgente(),
                r.getStatut().name(),
                r.getDateCreation()
        );
    }

    // Récupérer tous les chefs d'équipe (indépendamment de la spécialité, à affiner si nécessaire)
    public List<Utilisateur> getChefsEquipe(Specialite specialite) {
        // Si vous avez une méthode findByRoleAndSpecialite, utilisez-la.
        // Sinon, filtrez manuellement.
        return utilisateurRepository.findByRole(Role.CHEF_EQUIPE);
    }

    // Créer une intervention (avec chef d'équipe)
    @Transactional
    public InterventionResponse creerIntervention(InterventionRequest request, Long ordonnanceurId) {
        Reclamation reclamation = reclamationRepository.findById(request.getReclamationId())
                .orElseThrow(() -> new RuntimeException("Réclamation non trouvée"));
        if (reclamation.getStatut() != Reclamation.StatutReclamation.QUALIFIEE) {
            throw new RuntimeException("La réclamation doit d'abord être qualifiée");
        }
        TypeIntervention type = typeInterventionRepository.findById(request.getTypeInterventionId())
                .orElseThrow(() -> new RuntimeException("Type d'intervention invalide"));

        Ordonnanceur ordonnanceur = (Ordonnanceur) utilisateurRepository.findById(ordonnanceurId)
                .orElseThrow(() -> new RuntimeException("Ordonnanceur non trouvé"));

        // Récupérer le chef d'équipe
        ChefEquipe chefEquipe = null;
        if (request.getChefEquipeId() != null) {
            Utilisateur utilisateur = utilisateurRepository.findById(request.getChefEquipeId())
                    .orElseThrow(() -> new RuntimeException("Chef d'équipe non trouvé"));
            if (!(utilisateur instanceof ChefEquipe)) {
                throw new RuntimeException("L'utilisateur sélectionné n'est pas un chef d'équipe");
            }
            chefEquipe = (ChefEquipe) utilisateur;
        } else {
            throw new RuntimeException("Le chef d'équipe est obligatoire");
        }

        Intervention intervention = new Intervention();
        intervention.setUrgente(request.isUrgente());
        intervention.setStatut(Intervention.StatutIntervention.EN_ATTENTE);
        intervention.setDateCreation(LocalDateTime.now());
        intervention.setReclamation(reclamation);
        intervention.setOrdonnanceur(ordonnanceur);
        intervention.setTypeIntervention(type);
        intervention.setChefEquipe(chefEquipe);

        String annee = String.valueOf(Year.now().getValue());
        long count = interventionRepository.count() + 1;
        String numero = String.format("INT-%s-%05d", annee, count);
        intervention.setNumero(numero);

        Intervention saved = interventionRepository.save(intervention);
        reclamation.setStatut(Reclamation.StatutReclamation.INTERVENTION_CREE);
        reclamation.setIntervention(saved);
        reclamationRepository.save(reclamation);

        // Construction de la réponse avec tous les champs nécessaires
        return new InterventionResponse(
                saved.getId(),
                saved.getNumero(),
                saved.isUrgente(),
                saved.getStatut().name(),
                saved.getDateCreation(),
                reclamation.getId(),
                reclamation.getNumero(),                     // numéro de réclamation
                saved.getTypeIntervention().getId(),
                saved.getTypeIntervention().getLibelle(),    // libellé du type
                saved.getChefEquipe() != null ? saved.getChefEquipe().getId() : null,
                saved.getChefEquipe() != null ? saved.getChefEquipe().getNom() + " " + saved.getChefEquipe().getPrenom() : null
        );
    }

    // Récupérer les interventions créées par un ordonnanceur (pour son suivi)
    public List<InterventionResponse> getInterventions(Long ordonnanceurId) {
        List<Intervention> interventions = interventionRepository.findByOrdonnanceurId(ordonnanceurId);
        return interventions.stream().map(i -> new InterventionResponse(
                i.getId(),
                i.getNumero(),
                i.isUrgente(),
                i.getStatut().name(),
                i.getDateCreation(),
                i.getReclamation().getId(),
                i.getReclamation().getNumero(),
                i.getTypeIntervention().getId(),
                i.getTypeIntervention().getLibelle(),
                i.getChefEquipe() != null ? i.getChefEquipe().getId() : null,
                i.getChefEquipe() != null ? i.getChefEquipe().getNom() + " " + i.getChefEquipe().getPrenom() : null
        )).collect(Collectors.toList());
    }
}