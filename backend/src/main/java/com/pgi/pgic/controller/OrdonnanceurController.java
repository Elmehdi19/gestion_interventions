package com.pgi.pgic.controller;

import com.pgi.pgic.dto.*;
import com.pgi.pgic.entity.Specialite;
import com.pgi.pgic.entity.Utilisateur;
import com.pgi.pgic.security.UserPrincipal;
import com.pgi.pgic.service.OrdonnanceurService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ordonnanceur")
public class OrdonnanceurController {

    private final OrdonnanceurService ordonnanceurService;

    public OrdonnanceurController(OrdonnanceurService ordonnanceurService) {
        this.ordonnanceurService = ordonnanceurService;
    }

    @GetMapping("/reclamations")
    public ResponseEntity<List<ReclamationResponse>> getReclamationsNouvelles(
            @AuthenticationPrincipal UserPrincipal currentUser) {
        Specialite specialite = currentUser.getRole().toString().contains("ELECTRICITE") 
                ? Specialite.ELECTRICITE 
                : Specialite.EAU_ASSAINISSEMENT;
        return ResponseEntity.ok(ordonnanceurService.getReclamationsNouvelles(specialite));
    }

    @PutMapping("/reclamations/{id}/qualifier")
    public ResponseEntity<Void> qualifierReclamation(
            @PathVariable Long id,
            @RequestBody @Valid ReclamationQualificationRequest request,
            @AuthenticationPrincipal UserPrincipal currentUser) {
        ordonnanceurService.qualifierReclamation(id, currentUser.getId(), request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/types/arbre")
    public ResponseEntity<TypeArbreResponse> getArbreTypes(@AuthenticationPrincipal UserPrincipal currentUser) {
        Specialite specialite = currentUser.getRole().toString().contains("ELECTRICITE") 
                ? Specialite.ELECTRICITE 
                : Specialite.EAU_ASSAINISSEMENT;
        return ResponseEntity.ok(ordonnanceurService.getArbreTypes(specialite));
    }

    @PostMapping("/interventions")
    public ResponseEntity<InterventionResponse> creerIntervention(
            @RequestBody @Valid InterventionRequest request,
            @AuthenticationPrincipal UserPrincipal currentUser) {
        InterventionResponse response = ordonnanceurService.creerIntervention(request, currentUser.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/reclamations/qualifiees")
    public ResponseEntity<List<ReclamationResponse>> getReclamationsQualifiees(
            @AuthenticationPrincipal UserPrincipal currentUser) {
        Specialite specialite = currentUser.getRole().toString().contains("ELECTRICITE") 
                ? Specialite.ELECTRICITE 
                : Specialite.EAU_ASSAINISSEMENT;
        return ResponseEntity.ok(ordonnanceurService.getReclamationsQualifieesSansIntervention(specialite));
    }

    @GetMapping("/chefs-equipe")
    public ResponseEntity<List<ChefEquipeResponse>> getChefsEquipe(@AuthenticationPrincipal UserPrincipal currentUser) {
        Specialite specialite = currentUser.getRole().toString().contains("ELECTRICITE") 
                ? Specialite.ELECTRICITE 
                : Specialite.EAU_ASSAINISSEMENT;
        List<Utilisateur> chefs = ordonnanceurService.getChefsEquipe(specialite);
        List<ChefEquipeResponse> response = chefs.stream()
                .map(c -> new ChefEquipeResponse(c.getId(), c.getNom(), c.getPrenom(), c.getEmail()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/interventions")
    public ResponseEntity<List<InterventionResponse>> getInterventions(@AuthenticationPrincipal UserPrincipal currentUser) {
        // La méthode du service retourne directement une liste de InterventionResponse complète
        List<InterventionResponse> response = ordonnanceurService.getInterventions(currentUser.getId());
        return ResponseEntity.ok(response);
    }
}