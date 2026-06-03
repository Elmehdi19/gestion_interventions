package com.pgi.pgic.dto;

import com.pgi.pgic.entity.Specialite;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ReclamationRequest {
    @NotBlank(message = "La description est obligatoire")
    private String description;

    private String typeProbleme;
    private boolean urgente;
    private String piecesJointes;
    private Specialite specialiteConcerne;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypeProbleme() {
        return typeProbleme;
    }

    public void setTypeProbleme(String typeProbleme) {
        this.typeProbleme = typeProbleme;
    }

    public boolean isUrgente() {
        return urgente;
    }

    public void setUrgente(boolean urgente) {
        this.urgente = urgente;
    }

    public String getPiecesJointes() {
        return piecesJointes;
    }

    public void setPiecesJointes(String piecesJointes) {
        this.piecesJointes = piecesJointes;
    }

    public Specialite getSpecialiteConcerne() {
        return specialiteConcerne;
    }

    public void setSpecialiteConcerne(Specialite specialiteConcerne) {
        this.specialiteConcerne = specialiteConcerne;
    }
}