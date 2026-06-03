package com.pgi.pgic.dto;

public class TypeInterventionResponse {
    private Long id;
    private String libelle;
    private Integer dureeEstimeeMinutes;
    private Long familleId;
    private String familleLibelle;
    private Long specialiteId;
    private String specialiteLibelle;

    public TypeInterventionResponse(Long id, String libelle, Integer dureeEstimeeMinutes,
                                    Long familleId, String familleLibelle,
                                    Long specialiteId, String specialiteLibelle) {
        this.id = id;
        this.libelle = libelle;
        this.dureeEstimeeMinutes = dureeEstimeeMinutes;
        this.familleId = familleId;
        this.familleLibelle = familleLibelle;
        this.specialiteId = specialiteId;
        this.specialiteLibelle = specialiteLibelle;
    }
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

    public void setDureeEstimeeMinutes(Integer dureeEstimeeMinutes) {
        this.dureeEstimeeMinutes = dureeEstimeeMinutes;
    }

    public Long getFamilleId() {
        return familleId;
    }

    public void setFamilleId(Long familleId) {
        this.familleId = familleId;
    }

    public String getFamilleLibelle() {
        return familleLibelle;
    }

    public void setFamilleLibelle(String familleLibelle) {
        this.familleLibelle = familleLibelle;
    }

    public Long getSpecialiteId() {
        return specialiteId;
    }

    public void setSpecialiteId(Long specialiteId) {
        this.specialiteId = specialiteId;
    }

    public String getSpecialiteLibelle() {
        return specialiteLibelle;
    }

    public void setSpecialiteLibelle(String specialiteLibelle) {
        this.specialiteLibelle = specialiteLibelle;
    }
}