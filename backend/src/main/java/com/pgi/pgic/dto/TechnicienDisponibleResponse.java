package com.pgi.pgic.dto;

public class TechnicienDisponibleResponse {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String equipeNom;
    private int interventionsEnCours;

    public TechnicienDisponibleResponse(Long id, String nom, String prenom, String email, String equipeNom, int interventionsEnCours) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.equipeNom = equipeNom;
        this.interventionsEnCours = interventionsEnCours;
    }

    // Getters et setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getEquipeNom() { return equipeNom; }
    public void setEquipeNom(String equipeNom) { this.equipeNom = equipeNom; }
    public int getInterventionsEnCours() { return interventionsEnCours; }
    public void setInterventionsEnCours(int interventionsEnCours) { this.interventionsEnCours = interventionsEnCours; }
}