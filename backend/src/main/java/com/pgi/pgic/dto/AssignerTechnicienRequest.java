package com.pgi.pgic.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AssignerTechnicienRequest {
    @NotNull
    private Long technicienId;

    public Long getTechnicienId() {
        return technicienId;
    }

    public void setTechnicienId(Long technicienId) {
        this.technicienId = technicienId;
    }
}