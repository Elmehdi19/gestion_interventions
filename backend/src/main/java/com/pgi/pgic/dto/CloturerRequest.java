package com.pgi.pgic.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class CloturerRequest {
    @NotBlank
    private String numeroReclamation;
    private String rapport;
    private String materiauxUtilises;

    public String getNumeroReclamation() {
        return numeroReclamation;
    }

    public void setNumeroReclamation(String numeroReclamation) {
        this.numeroReclamation = numeroReclamation;
    }

    public String getRapport() {
        return rapport;
    }

    public void setRapport(String rapport) {
        this.rapport = rapport;
    }

    public String getMateriauxUtilises() {
        return materiauxUtilises;
    }

    public void setMateriauxUtilises(String materiauxUtilises) {
        this.materiauxUtilises = materiauxUtilises;
    }
}
