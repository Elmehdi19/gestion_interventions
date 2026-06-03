package com.pgi.pgic.controller;

import com.pgi.pgic.dto.PermutationRequest;
import com.pgi.pgic.dto.PermutationResponse;
import com.pgi.pgic.security.UserPrincipal;
import com.pgi.pgic.service.PermutationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/permutations")
public class PermutationController {

    @Autowired private PermutationService permutationService;

    @PostMapping
    @PreAuthorize("hasRole('CHEF_EQUIPE')")
    public ResponseEntity<PermutationResponse> demanderPermutation(
            @Valid @RequestBody PermutationRequest request,
            @AuthenticationPrincipal UserPrincipal currentUser) {
        PermutationResponse response = permutationService.demanderPermutation(request, currentUser.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}/valider")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PermutationResponse> validerPermutation(
            @PathVariable Long id,
            @AuthenticationPrincipal UserPrincipal currentUser) {
        PermutationResponse response = permutationService.validerPermutation(id, currentUser.getId());
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<PermutationResponse>> getAllPermutations() {
        return ResponseEntity.ok(permutationService.getAllPermutations());
    }
}