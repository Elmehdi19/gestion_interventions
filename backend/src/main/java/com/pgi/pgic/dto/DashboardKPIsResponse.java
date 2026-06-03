package com.pgi.pgic.dto;

import lombok.Data;
import java.util.Map;

@Data
public class DashboardKPIsResponse {
    private long totalReclamations;
    private long reclamationsOuvertes;
    private long reclamationsCloturees;
    private long totalInterventions;
    private long interventionsEnAttente;
    private long interventionsEnCours;
    private long interventionsCloturees;
    private double delaiMoyenPriseEnChargeHeures; // entre dateCreation et datePriseEnCharge
    private double tauxResolutionSLA; // % respectant SLA
    private Map<String, Long> reclamationsParSpecialite;
    private Map<String, Long> interventionsParType;
    private Map<String, Long> reclamationsParEquipe; // ex: "Matin" -> 10, "Nuit" -> 5
    private Map<String, Long> reclamationsParTechnicien; // ex: "John Doe" -> 8, "Jane Smith" -> 7
    private Map<String, Long> reclamationsParJour; // ex: "2024-01-01" -> 3, "2024-01-02" -> 5
    private Map<String, Long> reclamationsParMois; // ex: "2024-01" -> 20, "2024-02" -> 15
    private Map<String, Long> reclamationsParAnnee; // ex: "2024" -> 100, "2023" -> 80
    private Map<String, Long> reclamationsParStatut; // ex: "Ouverte" -> 12, "Cloturée" -> 18

    public long getTotalReclamations() {
        return totalReclamations;
    }

    public void setTotalReclamations(long totalReclamations) {
        this.totalReclamations = totalReclamations;
    }

    public long getReclamationsOuvertes() {
        return reclamationsOuvertes;
    }

    public void setReclamationsOuvertes(long reclamationsOuvertes) {
        this.reclamationsOuvertes = reclamationsOuvertes;
    }

    public long getReclamationsCloturees() {
        return reclamationsCloturees;
    }

    public void setReclamationsCloturees(long reclamationsCloturees) {
        this.reclamationsCloturees = reclamationsCloturees;
    }

    public long getTotalInterventions() {
        return totalInterventions;
    }

    public void setTotalInterventions(long totalInterventions) {
        this.totalInterventions = totalInterventions;
    }

    public long getInterventionsEnAttente() {
        return interventionsEnAttente;
    }

    public void setInterventionsEnAttente(long interventionsEnAttente) {
        this.interventionsEnAttente = interventionsEnAttente;
    }

    public long getInterventionsEnCours() {
        return interventionsEnCours;
    }

    public void setInterventionsEnCours(long interventionsEnCours) {
        this.interventionsEnCours = interventionsEnCours;
    }

    public long getInterventionsCloturees() {
        return interventionsCloturees;
    }

    public void setInterventionsCloturees(long interventionsCloturees) {
        this.interventionsCloturees = interventionsCloturees;
    }

    public double getDelaiMoyenPriseEnChargeHeures() {
        return delaiMoyenPriseEnChargeHeures;
    }

    public void setDelaiMoyenPriseEnChargeHeures(double delaiMoyenPriseEnChargeHeures) {
        this.delaiMoyenPriseEnChargeHeures = delaiMoyenPriseEnChargeHeures;
    }

    public double getTauxResolutionSLA() {
        return tauxResolutionSLA;
    }

    public void setTauxResolutionSLA(double tauxResolutionSLA) {
        this.tauxResolutionSLA = tauxResolutionSLA;
    }

    public Map<String, Long> getReclamationsParSpecialite() {
        return reclamationsParSpecialite;
    }

    public void setReclamationsParSpecialite(Map<String, Long> reclamationsParSpecialite) {
        this.reclamationsParSpecialite = reclamationsParSpecialite;
    }

    public Map<String, Long> getInterventionsParType() {
        return interventionsParType;
    }

    public void setInterventionsParType(Map<String, Long> interventionsParType) {
        this.interventionsParType = interventionsParType;
    }

    public Map<String, Long> getReclamationsParEquipe() {
        return reclamationsParEquipe;
    }

    public void setReclamationsParEquipe(Map<String, Long> reclamationsParEquipe) {
        this.reclamationsParEquipe = reclamationsParEquipe;
    }

    public Map<String, Long> getReclamationsParTechnicien() {
        return reclamationsParTechnicien;
    }

    public void setReclamationsParTechnicien(Map<String, Long> reclamationsParTechnicien) {
        this.reclamationsParTechnicien = reclamationsParTechnicien;
    }

    public Map<String, Long> getReclamationsParJour() {
        return reclamationsParJour;
    }

    public void setReclamationsParJour(Map<String, Long> reclamationsParJour) {
        this.reclamationsParJour = reclamationsParJour;
    }

    public Map<String, Long> getReclamationsParMois() {
        return reclamationsParMois;
    }

    public void setReclamationsParMois(Map<String, Long> reclamationsParMois) {
        this.reclamationsParMois = reclamationsParMois;
    }

    public Map<String, Long> getReclamationsParAnnee() {
        return reclamationsParAnnee;
    }

    public void setReclamationsParAnnee(Map<String, Long> reclamationsParAnnee) {
        this.reclamationsParAnnee = reclamationsParAnnee;
    }

    public Map<String, Long> getReclamationsParStatut() {
        return reclamationsParStatut;
    }

    public void setReclamationsParStatut(Map<String, Long> reclamationsParStatut) {
        this.reclamationsParStatut = reclamationsParStatut;
    }
   
}