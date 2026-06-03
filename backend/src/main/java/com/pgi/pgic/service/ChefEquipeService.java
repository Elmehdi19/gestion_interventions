package com.pgi.pgic.service;

import com.pgi.pgic.dto.*;
import com.pgi.pgic.entity.*;
import com.pgi.pgic.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChefEquipeService {

    @Autowired private InterventionRepository interventionRepository;
    @Autowired private TechnicienRepository technicienRepository;
    @Autowired private UtilisateurRepository utilisateurRepository;
    @Autowired private EquipeRepository equipeRepository;
    @Autowired private ChefEquipeRepository chefEquipeRepository;
    // ---------- Interventions ----------
    public Page<InterventionResponse> getInterventionsEnAttente(Long equipeId, Pageable pageable) {
        Page<Intervention> interventions = interventionRepository.findInterventionsByStatutWithFetch(Intervention.StatutIntervention.EN_ATTENTE, pageable);
        return interventions.map(this::toInterventionResponse);
    }

    public long getInterventionsEnAttenteCount() {
        return interventionRepository.countByStatut(Intervention.StatutIntervention.EN_ATTENTE);
    }

    // ---------- Techniciens ----------
    public List<TechnicienDTO> getAllTechniciens() {
        List<Technicien> techniciens = technicienRepository.findAll();
        return techniciens.stream()
                .map(t -> new TechnicienDTO(
                        t.getId(),
                        t.getNom(),
                        t.getPrenom(),
                        t.getEmail(),
                        t.getSpecialite() != null ? t.getSpecialite().name() : null,
                        t.getEquipe() != null ? t.getEquipe().getNom() : null,
                        interventionRepository.countActiveInterventionsByTechnicienId(t.getId())
                ))
                .collect(Collectors.toList());
    }

    public List<TechnicienDisponibleResponse> getTechniciensDisponibles(Long chefEquipeId) {
        List<Technicien> techniciens = technicienRepository.findAll();
        return techniciens.stream()
                .map(t -> new TechnicienDisponibleResponse(
                        t.getId(),
                        t.getNom(),
                        t.getPrenom(),
                        t.getEmail(),
                        t.getEquipe() != null ? t.getEquipe().getNom() : null,
                        interventionRepository.countActiveInterventionsByTechnicienId(t.getId())
                ))
                .sorted(Comparator.comparingInt(TechnicienDisponibleResponse::getInterventionsEnCours))
                .collect(Collectors.toList());
    }

    @Transactional
    public void affecterTechnicien(Long interventionId, Long technicienId, Long chefEquipeId) {
        Intervention intervention = interventionRepository.findById(interventionId)
                .orElseThrow(() -> new RuntimeException("Intervention non trouvée"));
        if (intervention.getStatut() != Intervention.StatutIntervention.EN_ATTENTE) {
            throw new RuntimeException("L'intervention n'est pas en attente d'affectation");
        }
        Technicien technicien = technicienRepository.findById(technicienId)
                .orElseThrow(() -> new RuntimeException("Technicien non trouvé"));
        intervention.setTechnicien(technicien);
        intervention.setStatut(Intervention.StatutIntervention.PRIS_EN_CHARGE);
        intervention.setDatePriseEnCharge(LocalDateTime.now());
        interventionRepository.save(intervention);
    }

    // ---------- Performances ----------
    public List<PerformanceEquipeResponse> getPerformancesEquipes() {
        List<Equipe> equipes = equipeRepository.findAll();
        return equipes.stream().map(equipe -> {
            List<Technicien> techniciens = technicienRepository.findByEquipe(equipe);
            long nbInterventionsTotal = 0;
            long nbCloturees = 0;
            long nbEnCours = 0;
            double sommeDuree = 0;
            int nbAvecDuree = 0;

            List<PerformanceEquipeResponse.TechnicienPerformanceLight> techPerformances = new ArrayList<>();
            for (Technicien tech : techniciens) {
                long nbIntervTech = interventionRepository.countByTechnicienId(tech.getId());
                long nbClotTech = interventionRepository.countByTechnicienIdAndStatut(tech.getId(), Intervention.StatutIntervention.CLOTURE);
                long nbEnCoursTech = interventionRepository.countByTechnicienIdAndStatut(tech.getId(), Intervention.StatutIntervention.EN_COURS)
                        + interventionRepository.countByTechnicienIdAndStatut(tech.getId(), Intervention.StatutIntervention.PRIS_EN_CHARGE);
                nbInterventionsTotal += nbIntervTech;
                nbCloturees += nbClotTech;
                nbEnCours += nbEnCoursTech;

                Double dureeMoy = interventionRepository.getAverageDurationByTechnicien(tech.getId());
                if (dureeMoy != null && dureeMoy > 0) {
                    sommeDuree += dureeMoy;
                    nbAvecDuree++;
                }

                PerformanceEquipeResponse.TechnicienPerformanceLight tpl = new PerformanceEquipeResponse.TechnicienPerformanceLight();
                tpl.setId(tech.getId());
                tpl.setNom(tech.getNom());
                tpl.setPrenom(tech.getPrenom());
                tpl.setNbInterventions(nbIntervTech);
                tpl.setNbCloturees(nbClotTech);
                techPerformances.add(tpl);
            }

            PerformanceEquipeResponse response = new PerformanceEquipeResponse();
            response.setEquipeId(equipe.getId());
            response.setNomEquipe(equipe.getNom());
            response.setChefNom(equipe.getChef() != null ? equipe.getChef().getPrenom() + " " + equipe.getChef().getNom() : null);
            response.setNbTechniciens(techniciens.size());
            response.setNbInterventionsTotal(nbInterventionsTotal);
            response.setNbCloturees(nbCloturees);
            response.setNbEnCours(nbEnCours);
            response.setDureeMoyenneEquipe(nbAvecDuree == 0 ? 0 : sommeDuree / nbAvecDuree);
            response.setTechniciens(techPerformances);
            return response;
        }).collect(Collectors.toList());
    }

    // ---------- Gestion des équipes (CRUD) ----------
    public List<EquipeResponse> getAllEquipes() {
        List<Equipe> equipes = equipeRepository.findAll();
        return equipes.stream().map(this::toEquipeResponse).collect(Collectors.toList());
    }

    public EquipeResponse getEquipeById(Long id) {
        Equipe equipe = equipeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Équipe non trouvée"));
        return toEquipeResponse(equipe);
    }

    @Transactional
public EquipeResponse createEquipe(EquipeRequest request) {
    Equipe equipe = new Equipe();
    equipe.setNom(request.getNom());
    equipe.setCreneauHoraire(request.getCreneauHoraire());

    if (request.getChefId() != null) {
        ChefEquipe chef = chefEquipeRepository.findById(request.getChefId())
                .orElseThrow(() -> new RuntimeException("Chef d'équipe non trouvé"));
        equipe.setChef(chef);
    }

    if (request.getSpecialite() != null && !request.getSpecialite().isEmpty()) {
        try {
            equipe.setSpecialite(Specialite.valueOf(request.getSpecialite().toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Spécialité invalide : " + request.getSpecialite());
        }
    }

    Equipe saved = equipeRepository.save(equipe);
    return toEquipeResponse(saved);
}

@Transactional
public EquipeResponse updateEquipe(Long id, EquipeRequest request) {
    Equipe equipe = equipeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Équipe non trouvée"));
    equipe.setNom(request.getNom());
    equipe.setCreneauHoraire(request.getCreneauHoraire());

    if (request.getChefId() != null) {
        ChefEquipe chef = chefEquipeRepository.findById(request.getChefId())
                .orElseThrow(() -> new RuntimeException("Chef d'équipe non trouvé"));
        equipe.setChef(chef);
    } else {
        equipe.setChef(null);
    }

    if (request.getSpecialite() != null && !request.getSpecialite().isEmpty()) {
        try {
            equipe.setSpecialite(Specialite.valueOf(request.getSpecialite().toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Spécialité invalide : " + request.getSpecialite());
        }
    } else {
        equipe.setSpecialite(null);
    }

    return toEquipeResponse(equipeRepository.save(equipe));
}

    @Transactional
    public void deleteEquipe(Long id) {
        equipeRepository.deleteById(id);
    }

    // ---------- Conversions privées ----------
    private InterventionResponse toInterventionResponse(Intervention i) {
        return new InterventionResponse(
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
        );
    }

    private EquipeResponse toEquipeResponse(Equipe equipe) {
        EquipeResponse resp = new EquipeResponse();
        resp.setId(equipe.getId());
        resp.setNom(equipe.getNom());
        resp.setCreneauHoraire(equipe.getCreneauHoraire());
        resp.setChefId(equipe.getChef() != null ? equipe.getChef().getId() : null);
        resp.setChefNom(equipe.getChef() != null ? equipe.getChef().getPrenom() + " " + equipe.getChef().getNom() : null);
        resp.setSpecialite(equipe.getSpecialite() != null ? equipe.getSpecialite().name() : null);
        resp.setNbTechniciens(equipe.getTechniciens() != null ? equipe.getTechniciens().size() : 0);

        if (equipe.getTechniciens() != null) {
            List<EquipeResponse.TechnicienSimpleResponse> techniciens = equipe.getTechniciens().stream()
                    .map(t -> new EquipeResponse.TechnicienSimpleResponse(t.getId(), t.getNom(), t.getPrenom()))
                    .collect(Collectors.toList());
            resp.setTechniciens(techniciens);
        }
        return resp;
    }
    public List<PerformanceTechnicienResponse> getPerformancesTechniciens() {
    List<Technicien> techniciens = technicienRepository.findAll();
    return techniciens.stream().map(tech -> {
        long nbInterventions = interventionRepository.countByTechnicienId(tech.getId());
        long nbCloturees = interventionRepository.countByTechnicienIdAndStatut(tech.getId(), Intervention.StatutIntervention.CLOTURE);
        long nbEmpechements = interventionRepository.countByTechnicienIdAndStatut(tech.getId(), Intervention.StatutIntervention.EMPECHE);
        Double dureeMoyenne = interventionRepository.getAverageDurationByTechnicien(tech.getId());
        double dureeMoyenneMinutes = dureeMoyenne != null ? dureeMoyenne : 0.0;
        double tauxCloture = nbInterventions == 0 ? 0 : (nbCloturees * 100.0 / nbInterventions);

        PerformanceTechnicienResponse perf = new PerformanceTechnicienResponse();
        perf.setTechnicienId(tech.getId());
        perf.setNomComplet(tech.getPrenom() + " " + tech.getNom());
        perf.setNbInterventions(nbInterventions);
        perf.setNbCloturees(nbCloturees);
        perf.setNbEmpechements(nbEmpechements);
        perf.setDureeMoyenneMinutes(dureeMoyenneMinutes);
        perf.setTauxCloture(tauxCloture);
        return perf;
    }).collect(Collectors.toList());
}
}