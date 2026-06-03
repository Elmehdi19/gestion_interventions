package com.pgi.pgic.dto;

public class FamilleArbreResponse {
    private Long id;
    private String libelle;
    public Long getId() {
        return id;
    }  
    public FamilleArbreResponse() {}

public FamilleArbreResponse(Long id, String libelle) {
    this.id = id;
    this.libelle = libelle;
}

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
}
