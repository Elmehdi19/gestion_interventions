package com.pgi.pgic.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class CloturerInterventionRequest {
    @NotBlank private String rapport;
    @NotNull private Integer dureeEffectiveMinutes;
    private String materiauxUtilises;

    public String getRapport() {
        return rapport;
    }

    public void setRapport(String rapport) {
        this.rapport = rapport;
    }

    public Integer getDureeEffectiveMinutes() {
        return dureeEffectiveMinutes;
    }

    public void setDureeEffectiveMinutes(Integer dureeEffectiveMinutes) {
        this.dureeEffectiveMinutes = dureeEffectiveMinutes;
    }

    public String getMateriauxUtilises() {
        return materiauxUtilises;
    }

    public void setMateriauxUtilises(String materiauxUtilises) {
        this.materiauxUtilises = materiauxUtilises;
    }

}