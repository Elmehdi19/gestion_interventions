package com.pgi.pgic.dto;

public class PerformanceTechnicienResponse {
    private Long technicienId;
    private String nomComplet;
    private long nbInterventions;
    private double dureeMoyenneMinutes;
    private long nbCloturees;
    private long nbEmpechements;
    private double tauxCloture;

    // Getters et setters (manuels)
    public Long getTechnicienId() { return technicienId; }
    public void setTechnicienId(Long technicienId) { this.technicienId = technicienId; }
    public String getNomComplet() { return nomComplet; }
    public void setNomComplet(String nomComplet) { this.nomComplet = nomComplet; }
    public long getNbInterventions() { return nbInterventions; }
    public void setNbInterventions(long nbInterventions) { this.nbInterventions = nbInterventions; }
    public double getDureeMoyenneMinutes() { return dureeMoyenneMinutes; }
    public void setDureeMoyenneMinutes(double dureeMoyenneMinutes) { this.dureeMoyenneMinutes = dureeMoyenneMinutes; }
    public long getNbCloturees() { return nbCloturees; }
    public void setNbCloturees(long nbCloturees) { this.nbCloturees = nbCloturees; }
    public long getNbEmpechements() { return nbEmpechements; }
    public void setNbEmpechements(long nbEmpechements) { this.nbEmpechements = nbEmpechements; }
    public double getTauxCloture() { return tauxCloture; }
    public void setTauxCloture(double tauxCloture) { this.tauxCloture = tauxCloture; }
}