package com.pgi.pgic.dto;

public class TypeArbreItemResponse {
    public TypeArbreItemResponse(Long id, String libelle, Integer dureeEstimeeMinutes) {
    this.id = id;
    this.libelle = libelle;
    this.dureeEstimeeMinutes = dureeEstimeeMinutes;
}

    private Long id;
    private String libelle;
    private Integer dureeEstimeeMinutes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Integer getDureeEstimeeMinutes() {
        return dureeEstimeeMinutes;
    }   
    
    
}
