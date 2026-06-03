package com.pgi.pgic.dto;

public class EquipeDTO {
    private Long id;
    private String nom;
    private String chefNom;
    private String specialite;            // spécialité de l’équipe
    private int nombreTechniciens;

    public EquipeDTO(Long id, String nom, String chefNom, String specialite, int nombreTechniciens) {
        this.id = id;
        this.nom = nom;
        this.chefNom = chefNom;
        this.specialite = specialite;
        this.nombreTechniciens = nombreTechniciens;
    }

    // Getters et setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getChefNom() {
        return chefNom;
    }

    public void setChefNom(String chefNom) {
        this.chefNom = chefNom;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public int getNombreTechniciens() {
        return nombreTechniciens;
    }

    public void setNombreTechniciens(int nombreTechniciens) {
        this.nombreTechniciens = nombreTechniciens;
    }
}