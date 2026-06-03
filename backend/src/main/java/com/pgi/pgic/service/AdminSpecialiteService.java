package com.pgi.pgic.service;

import com.pgi.pgic.dto.SpecialiteResponse;
import com.pgi.pgic.dto.SpecialiteRequest;
import com.pgi.pgic.entity.SpecialiteMetier;
import com.pgi.pgic.repository.SpecialiteMetierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminSpecialiteService {

    @Autowired
    private SpecialiteMetierRepository repository;

    public List<SpecialiteResponse> findAll() {
        return repository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    public SpecialiteResponse findById(Long id) {
        SpecialiteMetier entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Spécialité non trouvée"));
        return toResponse(entity);
    }

    public SpecialiteResponse create(SpecialiteRequest request) {
        SpecialiteMetier entity = new SpecialiteMetier();
        entity.setLibelle(request.getLibelle());
        SpecialiteMetier saved = repository.save(entity);
        return toResponse(saved);
    }

    public SpecialiteResponse update(Long id, SpecialiteRequest request) {
        SpecialiteMetier entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Spécialité non trouvée"));
        entity.setLibelle(request.getLibelle());
        SpecialiteMetier updated = repository.save(entity);
        return toResponse(updated);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) throw new RuntimeException("Spécialité non trouvée");
        repository.deleteById(id);
    }

    private SpecialiteResponse toResponse(SpecialiteMetier s) {
        SpecialiteResponse resp = new SpecialiteResponse();
        resp.setId(s.getId());
        resp.setLibelle(s.getLibelle());
        return resp;
    }
}