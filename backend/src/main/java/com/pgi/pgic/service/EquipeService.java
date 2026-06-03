package com.pgi.pgic.service;

import com.pgi.pgic.dto.EquipeResponse;
import com.pgi.pgic.entity.Equipe;
import com.pgi.pgic.entity.Technicien;
import com.pgi.pgic.repository.EquipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EquipeService {

    @Autowired private EquipeRepository equipeRepository;

    public List<EquipeResponse> getAllEquipes() {
        return equipeRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private EquipeResponse toResponse(Equipe equipe) {
        EquipeResponse resp = new EquipeResponse();
        resp.setId(equipe.getId());
        resp.setNom(equipe.getNom());
        resp.setCreneauHoraire(equipe.getCreneauHoraire());
        if (equipe.getTechniciens() != null) {
            resp.setTechniciens(equipe.getTechniciens().stream()
                    .map(t -> {
                        EquipeResponse.TechnicienSimpleResponse techResp = new EquipeResponse.TechnicienSimpleResponse();
                        techResp.setId(t.getId());
                        techResp.setNom(t.getNom());
                        techResp.setPrenom(t.getPrenom());
                        return techResp;
                    }).collect(Collectors.toList()));
        }
        return resp;
    }
}