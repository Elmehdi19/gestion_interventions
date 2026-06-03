package com.pgi.pgic.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmpecherInterventionRequest {
    @NotBlank private String motif;

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

}
