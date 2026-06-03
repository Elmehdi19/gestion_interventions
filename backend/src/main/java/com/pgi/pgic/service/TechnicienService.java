package com.pgi.pgic.service;

import com.pgi.pgic.dto.CloturerInterventionRequest;
import com.pgi.pgic.dto.EmpecherInterventionRequest;
import com.pgi.pgic.dto.InterventionResponse;
import com.pgi.pgic.entity.Intervention;
import com.pgi.pgic.entity.Reclamation;
import com.pgi.pgic.repository.InterventionRepository;
import com.pgi.pgic.repository.ReclamationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TechnicienService {

    @Autowired private InterventionRepository interventionRepository;
    @Autowired private ReclamationRepository reclamationRepository;

    @Transactional
    public void demarrerIntervention(Long interventionId, Long technicienId) {
        Intervention intervention = interventionRepository.findByIdAndTechnicienId(interventionId, technicienId)
                .orElseThrow(() -> new RuntimeException("Intervention non trouvée ou non affectée"));
        if (intervention.getStatut() != Intervention.StatutIntervention.PRIS_EN_CHARGE) {
            throw new RuntimeException("L'intervention doit être au statut PRIS_EN_CHARGE");
        }
        intervention.setStatut(Intervention.StatutIntervention.EN_COURS);
        intervention.setDateDebut(LocalDateTime.now());
        interventionRepository.save(intervention);
    }

    @Transactional
    public void clôturerIntervention(Long interventionId, Long technicienId, CloturerInterventionRequest request) {
        Intervention intervention = interventionRepository.findByIdAndTechnicienId(interventionId, technicienId)
                .orElseThrow(() -> new RuntimeException("Intervention non trouvée"));
        if (intervention.getStatut() != Intervention.StatutIntervention.EN_COURS) {
            throw new RuntimeException("L'intervention doit être en cours");
        }
        intervention.setStatut(Intervention.StatutIntervention.CLOTURE);
        intervention.setDateCloture(LocalDateTime.now());
        intervention.setRapport(request.getRapport());
        intervention.setDureeEffectiveMinutes(request.getDureeEffectiveMinutes());
        Reclamation reclamation = intervention.getReclamation();
        if (reclamation != null) {
            reclamation.setStatut(Reclamation.StatutReclamation.CLOTUREE);
            reclamationRepository.save(reclamation);
        }
        interventionRepository.save(intervention);
    }

    @Transactional
    public void signalerEmpechement(Long interventionId, Long technicienId, EmpecherInterventionRequest request) {
        Intervention intervention = interventionRepository.findByIdAndTechnicienId(interventionId, technicienId)
                .orElseThrow(() -> new RuntimeException("Intervention non trouvée"));
        if (intervention.getStatut() != Intervention.StatutIntervention.PRIS_EN_CHARGE && intervention.getStatut() != Intervention.StatutIntervention.EN_COURS) {
            throw new RuntimeException("Impossible de signaler un empêchement sur une intervention non prise en charge");
        }
        intervention.setStatut(Intervention.StatutIntervention.EMPECHE);
        intervention.setDateEmpechement(LocalDateTime.now());
        intervention.setRapport(request.getMotif()); 
        interventionRepository.save(intervention);
    }

    private InterventionResponse toResponse(Intervention i) {
        Long reclamationId = i.getReclamation() != null ? i.getReclamation().getId() : null;
        String reclamationNumero = i.getReclamation() != null ? i.getReclamation().getNumero() : null;
        Long typeId = i.getTypeIntervention() != null ? i.getTypeIntervention().getId() : null;
        String typeLibelle = i.getTypeIntervention() != null ? i.getTypeIntervention().getLibelle() : null;
        Long chefId = i.getChefEquipe() != null ? i.getChefEquipe().getId() : null;
        String chefNom = i.getChefEquipe() != null ? i.getChefEquipe().getNom() + " " + i.getChefEquipe().getPrenom() : null;

        return new InterventionResponse(
                i.getId(),
                i.getNumero(),
                i.isUrgente(),
                i.getStatut().name(),
                i.getDateCreation(),
                reclamationId,
                reclamationNumero,
                typeId,
                typeLibelle,
                chefId,
                chefNom
        );
    }
    public List<InterventionResponse> getMesInterventions(Long technicienId, Intervention.StatutIntervention statut) {
    List<Intervention> interventions;
    if (statut == null) {
        // Retourne toutes les interventions du technicien (quel que soit le statut)
        interventions = interventionRepository.findByTechnicienId(technicienId);
    } else {
        interventions = interventionRepository.findByTechnicienIdAndStatut(technicienId, statut);
    }
    return interventions.stream().map(this::toResponse).collect(Collectors.toList());
 }
}