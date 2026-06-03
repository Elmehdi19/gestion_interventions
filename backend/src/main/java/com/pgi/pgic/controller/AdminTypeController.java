package com.pgi.pgic.controller;

import com.pgi.pgic.dto.TypeInterventionResponse;
import com.pgi.pgic.entity.TypeIntervention;
import com.pgi.pgic.repository.TypeInterventionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin/types")
@PreAuthorize("hasRole('ADMIN')")
public class AdminTypeController {

    @Autowired
    private TypeInterventionRepository typeInterventionRepository;

    @GetMapping
    public ResponseEntity<List<TypeInterventionResponse>> getAll() {
        List<TypeIntervention> types = typeInterventionRepository.findAllWithRelations();
        List<TypeInterventionResponse> list = types.stream()
            .map(t -> {
                TypeInterventionResponse dto = new TypeInterventionResponse(
                    t.getId(),
                    t.getLibelle(),
                    t.getDureeEstimeeMinutes(),
                    t.getFamille().getId(),
                    t.getFamille().getLibelle(),
                    t.getFamille().getSpecialite().getId(),
                    t.getFamille().getSpecialite().getLibelle()
                );
                return dto;
            })
            .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeIntervention> getById(@PathVariable Long id) {
        return ResponseEntity.ok(typeInterventionRepository.findById(id).orElseThrow());
    }

    @PostMapping
    public ResponseEntity<TypeIntervention> create(@RequestBody TypeIntervention type) {
        return ResponseEntity.status(HttpStatus.CREATED).body(typeInterventionRepository.save(type));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TypeIntervention> update(@PathVariable Long id, @RequestBody TypeIntervention type) {
        type.setId(id);
        return ResponseEntity.ok(typeInterventionRepository.save(type));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        typeInterventionRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}