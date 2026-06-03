package com.pgi.pgic.dto;

public class ChefEquipeResponse {
    private Long id;
    private String nom;
    private String prenom;
    private String email;

    public ChefEquipeResponse(Long id, String nom, String prenom, String email) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    public Long getId() { return id; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getEmail() { return email; }
}