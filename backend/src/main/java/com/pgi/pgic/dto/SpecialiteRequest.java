package com.pgi.pgic.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SpecialiteRequest {
    @NotBlank private String libelle;

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}