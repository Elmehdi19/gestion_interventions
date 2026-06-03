package com.pgi.pgic.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class PermutationRequest {
    @NotNull private Long technicienId;
    @NotNull private Long equipeDestinationId;
    @NotNull private LocalDateTime dateDebut;
    @NotNull private LocalDateTime dateFin;
    private String motif;
    private String typePermutation; // IMMEDIATE, DIFFEREE, TEMPORAIRE

    public Long getTechnicienId() {
        return technicienId;
    }

    public void setTechnicienId(Long technicienId) {
        this.technicienId = technicienId;
    }

    public Long getEquipeDestinationId() {
        return equipeDestinationId;
    }

    public void setEquipeDestinationId(Long equipeDestinationId) {
        this.equipeDestinationId = equipeDestinationId;
    }

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDateTime getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDateTime dateFin) {
        this.dateFin = dateFin;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getTypePermutation() {
        return typePermutation;
    }

    public void setTypePermutation(String typePermutation) {
        this.typePermutation = typePermutation;
    }

}
