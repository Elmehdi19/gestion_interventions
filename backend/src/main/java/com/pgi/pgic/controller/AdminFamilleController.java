package com.pgi.pgic.controller;

import com.pgi.pgic.dto.FamilleResponse;
import com.pgi.pgic.entity.Famille;
import com.pgi.pgic.repository.FamilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin/familles")
@PreAuthorize("hasRole('ADMIN')")
public class AdminFamilleController {

    @Autowired
    private FamilleRepository familleRepository;

    @GetMapping
public ResponseEntity<List<FamilleResponse>> getAll() {
    List<FamilleResponse> list = familleRepository.findAll().stream()
        .map(f -> {
            FamilleResponse dto = new FamilleResponse();
            dto.setId(f.getId());
            dto.setLibelle(f.getLibelle());
            dto.setSpecialiteLibelle(f.getSpecialite().getLibelle());
            dto.setSpecialiteId(f.getSpecialite().getId());
            return dto;
        })
        .collect(Collectors.toList());
    return ResponseEntity.ok(list);
}

    @GetMapping("/{id}")
    public ResponseEntity<Famille> getById(@PathVariable Long id) {
        return ResponseEntity.ok(familleRepository.findById(id).orElseThrow());
    }

    @PostMapping
    public ResponseEntity<Famille> create(@RequestBody Famille famille) {
        return ResponseEntity.status(HttpStatus.CREATED).body(familleRepository.save(famille));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Famille> update(@PathVariable Long id, @RequestBody Famille famille) {
        famille.setId(id);
        return ResponseEntity.ok(familleRepository.save(famille));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        familleRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}