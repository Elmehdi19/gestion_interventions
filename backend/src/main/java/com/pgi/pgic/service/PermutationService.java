package com.pgi.pgic.service;

import com.pgi.pgic.dto.PermutationRequest;
import com.pgi.pgic.dto.PermutationResponse;
import com.pgi.pgic.entity.*;
import com.pgi.pgic.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermutationService {

    @Autowired private PermutationEquipeRepository permutationRepository;
    @Autowired private TechnicienRepository technicienRepository;
    @Autowired private EquipeRepository equipeRepository;
    @Autowired private UtilisateurRepository utilisateurRepository;

    @Transactional
    public PermutationResponse demanderPermutation(PermutationRequest request, Long chefDemandeurId) {
        Technicien technicien = technicienRepository.findById(request.getTechnicienId())
                .orElseThrow(() -> new RuntimeException("Technicien non trouvé"));
        Equipe destination = equipeRepository.findById(request.getEquipeDestinationId())
                .orElseThrow(() -> new RuntimeException("Équipe destination non trouvée"));
        ChefEquipe demandeur = (ChefEquipe) utilisateurRepository.findById(chefDemandeurId)
                .orElseThrow(() -> new RuntimeException("Chef d'équipe non trouvé"));

        PermutationEquipe perm = new PermutationEquipe();
        perm.setTechnicien(technicien);
        perm.setEquipeDestination(destination);
        perm.setDateDebut(request.getDateDebut());
        perm.setDateFin(request.getDateFin());
        perm.setMotif(request.getMotif());
        perm.setTypePermutation(request.getTypePermutation());
        perm.setStatut("EN_ATTENTE");
        perm.setDemandeur(demandeur);
        perm.setDateDemande(LocalDateTime.now());

        PermutationEquipe saved = permutationRepository.save(perm);
        return toResponse(saved);
    }

  
@Transactional
    public PermutationResponse validerPermutation(Long permutationId, Long adminId) {
        PermutationEquipe perm = permutationRepository.findById(permutationId)
                .orElseThrow(() -> new RuntimeException("Permutation non trouvée"));
        if (!"EN_ATTENTE".equals(perm.getStatut())) {
            throw new RuntimeException("Cette permutation a déjà été traitée");
        }
        // Appliquer la permutation
        Technicien tech = perm.getTechnicien();
        tech.setEquipe(perm.getEquipeDestination());
        technicienRepository.save(tech);
        perm.setStatut("VALIDEE");
        PermutationEquipe saved = permutationRepository.save(perm);
        return toResponse(saved);
    }

public List<PermutationResponse> getAllPermutations() {
    return permutationRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
}
    public List<PermutationResponse> getPermutationsEnAttente() {
        return permutationRepository.findByStatut("EN_ATTENTE").stream().map(this::toResponse).collect(Collectors.toList());
    }

    private PermutationResponse toResponse(PermutationEquipe p) {
        PermutationResponse resp = new PermutationResponse();
        resp.setId(p.getId());
        resp.setTechnicienId(p.getTechnicien().getId());
        resp.setTechnicienNom(p.getTechnicien().getNom() + " " + p.getTechnicien().getPrenom());
        resp.setEquipeDestinationId(p.getEquipeDestination().getId());
        resp.setEquipeDestinationNom(p.getEquipeDestination().getNom());
        resp.setDateDebut(p.getDateDebut());
        resp.setDateFin(p.getDateFin());
        resp.setMotif(p.getMotif());
        resp.setTypePermutation(p.getTypePermutation());
        resp.setStatut(p.getStatut());
        resp.setDateDemande(p.getDateDemande());
        return resp;
    }

}