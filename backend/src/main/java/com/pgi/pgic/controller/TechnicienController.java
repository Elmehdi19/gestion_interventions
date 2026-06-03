package com.pgi.pgic.controller;

import com.pgi.pgic.dto.CloturerInterventionRequest;
import com.pgi.pgic.dto.EmpecherInterventionRequest;
import com.pgi.pgic.dto.InterventionResponse;
import com.pgi.pgic.entity.Intervention;
import com.pgi.pgic.security.UserPrincipal;
import com.pgi.pgic.service.TechnicienService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/technicien")
@PreAuthorize("hasRole('TECHNICIEN')")
public class TechnicienController {

    @Autowired private TechnicienService technicienService;

    @GetMapping("/interventions")
    public ResponseEntity<List<InterventionResponse>> getMesInterventions(
            @RequestParam(required = false) String statut,
            @AuthenticationPrincipal UserPrincipal currentUser) {
        // Si aucun statut n'est fourni, on retourne toutes les interventions
        Intervention.StatutIntervention statutEnum = null;
        if (statut != null && !statut.isEmpty()) {
            statutEnum = Intervention.StatutIntervention.valueOf(statut.toUpperCase());
        }
        return ResponseEntity.ok(technicienService.getMesInterventions(currentUser.getId(), statutEnum));
    }

    @PutMapping("/interventions/{id}/demarrer")
    public ResponseEntity<Void> demarrer(@PathVariable Long id, @AuthenticationPrincipal UserPrincipal currentUser) {
        technicienService.demarrerIntervention(id, currentUser.getId());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/interventions/{id}/cloturer")
    public ResponseEntity<Void> cloturer(@PathVariable Long id,
                                         @RequestBody @Valid CloturerInterventionRequest request,
                                         @AuthenticationPrincipal UserPrincipal currentUser) {
        technicienService.clôturerIntervention(id, currentUser.getId(), request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/interventions/{id}/empecher")
    public ResponseEntity<Void> empecher(@PathVariable Long id,
                                         @RequestBody @Valid EmpecherInterventionRequest request,
                                         @AuthenticationPrincipal UserPrincipal currentUser) {
        technicienService.signalerEmpechement(id, currentUser.getId(), request);
        return ResponseEntity.ok().build();
    }
}