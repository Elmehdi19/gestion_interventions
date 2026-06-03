package com.pgi.pgic.controller;

import com.pgi.pgic.dto.*;
import com.pgi.pgic.entity.Role;
import com.pgi.pgic.entity.Intervention;
import com.pgi.pgic.entity.Utilisateur;
import com.pgi.pgic.repository.InterventionRepository;
import com.pgi.pgic.security.UserPrincipal;
import com.pgi.pgic.service.ChefEquipeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import com.pgi.pgic.repository.UtilisateurRepository;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/chef-equipe")
@PreAuthorize("hasRole('CHEF_EQUIPE')")
public class ChefEquipeController {

    @Autowired private ChefEquipeService chefEquipeService;
    @Autowired private InterventionRepository interventionRepository;
    @Autowired private UtilisateurRepository utilisateurRepository;

    // Interventions en attente (paginées)
    @GetMapping("/interventions/en-attente")
    public ResponseEntity<Page<InterventionResponse>> getInterventionsEnAttente(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @AuthenticationPrincipal UserPrincipal currentUser) {
        Pageable pageable = PageRequest.of(page, size,
                Sort.by(Sort.Direction.DESC, "urgente").and(Sort.by(Sort.Direction.ASC, "dateCreation")));
        Page<Intervention> interventionsPage = interventionRepository.findInterventionsByStatutWithFetch(
                Intervention.StatutIntervention.EN_ATTENTE, pageable);
        Page<InterventionResponse> responsePage = interventionsPage.map(this::toResponse);
        return ResponseEntity.ok(responsePage);
    }

    // Compter les interventions en attente
    @GetMapping("/interventions/en-attente/count")
    public ResponseEntity<Long> getInterventionsEnAttenteCount() {
        return ResponseEntity.ok(chefEquipeService.getInterventionsEnAttenteCount());
    }

    // Tous les techniciens
    @GetMapping("/techniciens")
    public ResponseEntity<List<TechnicienDTO>> getAllTechniciens() {
        return ResponseEntity.ok(chefEquipeService.getAllTechniciens());
    }

    // Techniciens disponibles pour affectation
    @GetMapping("/techniciens/disponibles")
    public ResponseEntity<List<TechnicienDisponibleResponse>> getTechniciensDisponibles(
            @AuthenticationPrincipal UserPrincipal currentUser) {
        return ResponseEntity.ok(chefEquipeService.getTechniciensDisponibles(currentUser.getId()));
    }

    // Affecter un technicien
    @PutMapping("/interventions/{interventionId}/affecter")
    public ResponseEntity<Void> affecterTechnicien(
            @PathVariable Long interventionId,
            @RequestBody AffectationRequest request,
            @AuthenticationPrincipal UserPrincipal currentUser) {
        chefEquipeService.affecterTechnicien(interventionId, request.getTechnicienId(), currentUser.getId());
        return ResponseEntity.ok().build();
    }

    private InterventionResponse toResponse(Intervention intervention) {
        return new InterventionResponse(
                intervention.getId(),
                intervention.getNumero(),
                intervention.isUrgente(),
                intervention.getStatut().name(),
                intervention.getDateCreation(),
                intervention.getReclamation().getId(),
                intervention.getReclamation().getNumero(),
                intervention.getTypeIntervention().getId(),
                intervention.getTypeIntervention().getLibelle(),
                intervention.getChefEquipe() != null ? intervention.getChefEquipe().getId() : null,
                intervention.getChefEquipe() != null ? intervention.getChefEquipe().getNom() + " " + intervention.getChefEquipe().getPrenom() : null
        );
    }
    @GetMapping("/performances/techniciens")
    public ResponseEntity<List<PerformanceTechnicienResponse>> getPerformancesTechniciens() {
        return ResponseEntity.ok(chefEquipeService.getPerformancesTechniciens());
    }
    @GetMapping("/performances/equipes")
    public ResponseEntity<List<PerformanceEquipeResponse>> getPerformancesEquipes() {
        return ResponseEntity.ok(chefEquipeService.getPerformancesEquipes());
    }
        @GetMapping("/equipes")
    public ResponseEntity<List<EquipeResponse>> getAllEquipes() {
        return ResponseEntity.ok(chefEquipeService.getAllEquipes());
    }

        @GetMapping("/equipes/{id}")
        public ResponseEntity<EquipeResponse> getEquipeById(@PathVariable Long id) {
            return ResponseEntity.ok(chefEquipeService.getEquipeById(id));
        }

    @PostMapping("/equipes")
    public ResponseEntity<EquipeResponse> createEquipe(@RequestBody EquipeRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(chefEquipeService.createEquipe(request));
    }

    @PutMapping("/equipes/{id}")
    public ResponseEntity<EquipeResponse> updateEquipe(@PathVariable Long id, @RequestBody EquipeRequest request) {
        return ResponseEntity.ok(chefEquipeService.updateEquipe(id, request));
    }

    @DeleteMapping("/equipes/{id}")
    public ResponseEntity<Void> deleteEquipe(@PathVariable Long id) {
        chefEquipeService.deleteEquipe(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/chefs")
    public ResponseEntity<List<ChefEquipeDTO>> getAllChefs() {
        List<Utilisateur> chefs = utilisateurRepository.findByRole(Role.CHEF_EQUIPE);
        List<ChefEquipeDTO> dtos = chefs.stream()
                .map(c -> new ChefEquipeDTO(c.getId(), c.getNom(), c.getPrenom(), c.getEmail()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}