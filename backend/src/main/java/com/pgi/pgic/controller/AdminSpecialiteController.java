package com.pgi.pgic.controller;

import com.pgi.pgic.entity.SpecialiteMetier;
import com.pgi.pgic.repository.SpecialiteMetierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/specialites")
@PreAuthorize("hasRole('ADMIN')")
public class AdminSpecialiteController {

    @Autowired
    private SpecialiteMetierRepository specialiteMetierRepository;

    @GetMapping
    public ResponseEntity<List<SpecialiteMetier>> getAll() {
        return ResponseEntity.ok(specialiteMetierRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecialiteMetier> getById(@PathVariable Long id) {
        return ResponseEntity.ok(specialiteMetierRepository.findById(id).orElseThrow());
    }

    @PostMapping
    public ResponseEntity<SpecialiteMetier> create(@RequestBody SpecialiteMetier specialite) {
        return ResponseEntity.status(HttpStatus.CREATED).body(specialiteMetierRepository.save(specialite));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpecialiteMetier> update(@PathVariable Long id, @RequestBody SpecialiteMetier specialite) {
        specialite.setId(id);
        return ResponseEntity.ok(specialiteMetierRepository.save(specialite));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        specialiteMetierRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}