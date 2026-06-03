package com.pgi.pgic.dto;

import jakarta.validation.constraints.NotNull;

public class InterventionRequest {
    @NotNull
    private Long reclamationId;
    @NotNull
    private Long typeInterventionId;
    private boolean urgente; // peut être hérité ou modifié
    private Long chefEquipeId;
    public Long getChefEquipeId() {
        return chefEquipeId;
    }
    public void setChefEquipeId(Long chefEquipeId) {
        this.chefEquipeId = chefEquipeId;
    }
    public Long getReclamationId() {
        return reclamationId;
    }
    public void setReclamationId(Long reclamationId) {
        this.reclamationId = reclamationId;
    }
    public Long getTypeInterventionId() {
        return typeInterventionId;
    }
    public void setTypeInterventionId(Long typeInterventionId) {
        this.typeInterventionId = typeInterventionId;
    }
    public boolean isUrgente() {
        return urgente;
    }
}