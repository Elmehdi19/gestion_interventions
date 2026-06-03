package com.pgi.pgic.controller;

import com.pgi.pgic.dto.SpecialiteResponse;
import com.pgi.pgic.repository.SpecialiteMetierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/public")
public class PublicController {

    @Autowired
    private SpecialiteMetierRepository specialiteMetierRepository;

    @GetMapping("/specialites")
    public ResponseEntity<List<SpecialiteResponse>> getSpecialites() {
        List<SpecialiteResponse> list = specialiteMetierRepository.findAll()
                .stream()
                .map(s -> {
                    SpecialiteResponse dto = new SpecialiteResponse();
                    dto.setId(s.getId());
                    dto.setLibelle(s.getLibelle());
                    return dto;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }
}