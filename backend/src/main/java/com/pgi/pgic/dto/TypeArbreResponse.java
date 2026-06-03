package com.pgi.pgic.dto;

import java.util.List;

public class TypeArbreResponse {
    private Long id;
    private String libelle;
    private List<FamilleArbreResponse> familles;

    // Constructeurs
    public TypeArbreResponse() {}

    public TypeArbreResponse(Long id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    // Getters et setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }

    public List<FamilleArbreResponse> getFamilles() { return familles; }
    public void setFamilles(List<FamilleArbreResponse> familles) { this.familles = familles; }

    // ========== CLASSE INTERNE FamilleArbreResponse ==========
    public static class FamilleArbreResponse {
        private Long id;
        private String libelle;
        private List<TypeArbreItemResponse> types;

        public FamilleArbreResponse() {}

        public FamilleArbreResponse(Long id, String libelle) {
            this.id = id;
            this.libelle = libelle;
        }

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

        public String getLibelle() { return libelle; }
        public void setLibelle(String libelle) { this.libelle = libelle; }

        public List<TypeArbreItemResponse> getTypes() { return types; }
        public void setTypes(List<TypeArbreItemResponse> types) { this.types = types; }
    }

    // ========== CLASSE INTERNE TypeArbreItemResponse ==========
    public static class TypeArbreItemResponse {
        private Long id;
        private String libelle;
        private Integer dureeEstimeeMinutes;

        public TypeArbreItemResponse() {}

        public TypeArbreItemResponse(Long id, String libelle, Integer dureeEstimeeMinutes) {
            this.id = id;
            this.libelle = libelle;
            this.dureeEstimeeMinutes = dureeEstimeeMinutes;
        }

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

        public String getLibelle() { return libelle; }
        public void setLibelle(String libelle) { this.libelle = libelle; }

        public Integer getDureeEstimeeMinutes() { return dureeEstimeeMinutes; }
        public void setDureeEstimeeMinutes(Integer dureeEstimeeMinutes) { this.dureeEstimeeMinutes = dureeEstimeeMinutes; }
    }
}