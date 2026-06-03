package com.pgi.pgic.service;

import com.pgi.pgic.dto.DashboardKPIsResponse;
import com.pgi.pgic.dto.PerformanceTechnicienResponse;
import com.pgi.pgic.repository.InterventionRepository;
import com.pgi.pgic.repository.ReclamationRepository;
import com.pgi.pgic.repository.TechnicienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    @Autowired private ReclamationRepository reclamationRepository;
    @Autowired private InterventionRepository interventionRepository;
    @Autowired private TechnicienRepository technicienRepository;

    public DashboardKPIsResponse getAdminKPIs() {
        DashboardKPIsResponse resp = new DashboardKPIsResponse();
        resp.setTotalReclamations(reclamationRepository.count());
        resp.setReclamationsOuvertes(0);
        resp.setReclamationsCloturees(0);
        resp.setTotalInterventions(0);
        resp.setInterventionsEnAttente(0);
        resp.setInterventionsEnCours(0);
        resp.setInterventionsCloturees(0);
        resp.setDelaiMoyenPriseEnChargeHeures(0.0);
        resp.setTauxResolutionSLA(0.0);
        resp.setReclamationsParSpecialite(new HashMap<>());
        resp.setInterventionsParType(new HashMap<>());
        resp.setReclamationsParEquipe(new HashMap<>());
        resp.setReclamationsParTechnicien(new HashMap<>());
        resp.setReclamationsParJour(new HashMap<>());
        resp.setReclamationsParMois(new HashMap<>());
        resp.setReclamationsParAnnee(new HashMap<>());
        resp.setReclamationsParStatut(new HashMap<>());
        return resp;
    }

    public List<PerformanceTechnicienResponse> getPerformancesTechniciens() {
        return technicienRepository.findAll().stream().map(t -> {
            PerformanceTechnicienResponse perf = new PerformanceTechnicienResponse();
            perf.setTechnicienId(t.getId());
            perf.setNomComplet(t.getNom() + " " + t.getPrenom());
            perf.setNbInterventions(0);
            perf.setDureeMoyenneMinutes(0.0);
            perf.setNbCloturees(0);
            perf.setNbEmpechements(0);
            perf.setTauxCloture(0.0);
            return perf;
        }).collect(Collectors.toList());
    }
}