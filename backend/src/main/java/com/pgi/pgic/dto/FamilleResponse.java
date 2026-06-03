package com.pgi.pgic.dto;

import lombok.Data;

@Data
public class FamilleResponse {
    private Long id;
    private String libelle;
    private String specialiteLibelle;
    private Long specialiteId;

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

    public String getSpecialiteLibelle() {
        return specialiteLibelle;
    }

    public void setSpecialiteLibelle(String specialiteLibelle) {
        this.specialiteLibelle = specialiteLibelle;
    }

    public Long getSpecialiteId() {
        return specialiteId;
    }

    public void setSpecialiteId(Long specialiteId) {
        this.specialiteId = specialiteId;
    }
}