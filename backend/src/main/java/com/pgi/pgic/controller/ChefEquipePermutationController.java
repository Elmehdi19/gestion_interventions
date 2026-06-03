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

@RestController
@RequestMapping("/api/chef-equipe")
@PreAuthorize("hasRole('CHEF_EQUIPE')")
public class ChefEquipePermutationController {

    @Autowired private PermutationService permutationService;

    @PostMapping("/permutations")
    public ResponseEntity<PermutationResponse> demanderPermutation(
            @RequestBody @Valid PermutationRequest request,
            @AuthenticationPrincipal UserPrincipal currentUser) {
        PermutationResponse response = permutationService.demanderPermutation(request, currentUser.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}