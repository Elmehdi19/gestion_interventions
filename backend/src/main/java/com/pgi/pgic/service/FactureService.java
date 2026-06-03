package com.pgi.pgic.service;

import com.pgi.pgic.dto.FactureResponse;
import com.pgi.pgic.entity.Client;
import com.pgi.pgic.entity.Facture;
import com.pgi.pgic.repository.FactureRepository;
import com.pgi.pgic.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FactureService {

    @Autowired private FactureRepository factureRepository;
    @Autowired private UtilisateurRepository utilisateurRepository;

    public List<FactureResponse> getFacturesByClient(Long clientId) {
        Client client = (Client) utilisateurRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client non trouvé"));
        List<Facture> factures = factureRepository.findByClientIdOrderByDateEmissionDesc(client.getId());
        return factures.stream().map(this::toResponse).collect(Collectors.toList());
    }

    private FactureResponse toResponse(Facture f) {
        FactureResponse resp = new FactureResponse();
        resp.setId(f.getId());
        resp.setNumeroFacture(f.getNumeroFacture());
        resp.setMontantTTC(f.getMontantTTC());
        resp.setDateEmission(f.getDateEmission());
        resp.setDateEcheance(f.getDateEcheance());
        resp.setStatut(f.getStatut().name());
        resp.setPdfUrl(f.getPdfUrl());
        resp.setPeriodeMois(f.getPeriodeMois());
        resp.setPeriodeAnnee(f.getPeriodeAnnee());
        return resp;
    }
}