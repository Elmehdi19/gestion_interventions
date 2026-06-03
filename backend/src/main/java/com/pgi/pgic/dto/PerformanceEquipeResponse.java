package com.pgi.pgic.dto;

import java.util.List;

public class PerformanceEquipeResponse {
    private Long equipeId;
    private String nomEquipe;
    private String chefNom;
    private int nbTechniciens;
    private long nbInterventionsTotal;
    private long nbCloturees;
    private long nbEnCours;
    private double dureeMoyenneEquipe;
    private List<TechnicienPerformanceLight> techniciens;

    // Getters et setters (ou Lombok)
    public Long getEquipeId() { return equipeId; }
    public void setEquipeId(Long equipeId) { this.equipeId = equipeId; }
    public String getNomEquipe() { return nomEquipe; }
    public void setNomEquipe(String nomEquipe) { this.nomEquipe = nomEquipe; }
    public String getChefNom() { return chefNom; }
    public void setChefNom(String chefNom) { this.chefNom = chefNom; }
    public int getNbTechniciens() { return nbTechniciens; }
    public void setNbTechniciens(int nbTechniciens) { this.nbTechniciens = nbTechniciens; }
    public long getNbInterventionsTotal() { return nbInterventionsTotal; }
    public void setNbInterventionsTotal(long nbInterventionsTotal) { this.nbInterventionsTotal = nbInterventionsTotal; }
    public long getNbCloturees() { return nbCloturees; }
    public void setNbCloturees(long nbCloturees) { this.nbCloturees = nbCloturees; }
    public long getNbEnCours() { return nbEnCours; }
    public void setNbEnCours(long nbEnCours) { this.nbEnCours = nbEnCours; }
    public double getDureeMoyenneEquipe() { return dureeMoyenneEquipe; }
    public void setDureeMoyenneEquipe(double dureeMoyenneEquipe) { this.dureeMoyenneEquipe = dureeMoyenneEquipe; }
    public List<TechnicienPerformanceLight> getTechniciens() { return techniciens; }
    public void setTechniciens(List<TechnicienPerformanceLight> techniciens) { this.techniciens = techniciens; }

    public static class TechnicienPerformanceLight {
        private Long id;
        private String nom;
        private String prenom;
        private long nbInterventions;
        private long nbCloturees;
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getNom() { return nom; }
        public void setNom(String nom) { this.nom = nom; }
        public String getPrenom() { return prenom; }
        public void setPrenom(String prenom) { this.prenom = prenom; }
        public long getNbInterventions() { return nbInterventions; }
        public void setNbInterventions(long nbInterventions) { this.nbInterventions = nbInterventions; }
        public long getNbCloturees() { return nbCloturees; }
        public void setNbCloturees(long nbCloturees) { this.nbCloturees = nbCloturees; }
    }
}