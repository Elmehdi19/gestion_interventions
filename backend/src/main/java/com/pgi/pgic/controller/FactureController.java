package com.pgi.pgic.controller;

import com.pgi.pgic.dto.FactureResponse;
import com.pgi.pgic.security.UserPrincipal;
import com.pgi.pgic.service.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/factures")
@PreAuthorize("hasRole('CLIENT')")
public class FactureController {

    @Autowired private FactureService factureService;

    @GetMapping("/client")
    public ResponseEntity<List<FactureResponse>> getMesFactures(@AuthenticationPrincipal UserPrincipal currentUser) {
        return ResponseEntity.ok(factureService.getFacturesByClient(currentUser.getId()));
    }
}