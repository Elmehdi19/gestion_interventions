package com.pgi.pgic.controller;

import com.pgi.pgic.dto.EquipeResponse;
import com.pgi.pgic.dto.PermutationResponse;
import com.pgi.pgic.service.EquipeService;
import com.pgi.pgic.service.PermutationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminEquipeController {

    @Autowired private EquipeService equipeService;
    @Autowired private PermutationService permutationService;

    @GetMapping("/equipes")
    public ResponseEntity<List<EquipeResponse>> getEquipes() {
        return ResponseEntity.ok(equipeService.getAllEquipes());
    }

    @GetMapping("/permutations/en-attente")
    public ResponseEntity<List<PermutationResponse>> getPermutationsEnAttente() {
        return ResponseEntity.ok(permutationService.getPermutationsEnAttente());
    }

    @PutMapping("/permutations/{id}/valider")
    public ResponseEntity<Void> validerPermutation(@PathVariable Long id) {
        permutationService.validerPermutation(id, null); // adminId optionnel
        return ResponseEntity.ok().build();
    }
}