package com.pgi.pgic.controller;

import com.pgi.pgic.dto.EquipeResponse;
import com.pgi.pgic.service.EquipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/equipes")
@PreAuthorize("hasAnyRole('ADMIN', 'CHEF_EQUIPE')")
public class EquipeController {

    @Autowired private EquipeService equipeService;

    @GetMapping
    public ResponseEntity<List<EquipeResponse>> getAllEquipes() {
        return ResponseEntity.ok(equipeService.getAllEquipes());
    }
}