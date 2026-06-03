package com.pgi.pgic.dto;

public class EquipeRequest {
    private String nom;
    private String creneauHoraire;   // nouveau
    private Long chefId;
    private String specialite;       // si vous l'utilisez

    // getters / setters
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getCreneauHoraire() { return creneauHoraire; }
    public void setCreneauHoraire(String creneauHoraire) { this.creneauHoraire = creneauHoraire; }
    public Long getChefId() { return chefId; }
    public void setChefId(Long chefId) { this.chefId = chefId; }
    public String getSpecialite() { return specialite; }
    public void setSpecialite(String specialite) { this.specialite = specialite; }
}