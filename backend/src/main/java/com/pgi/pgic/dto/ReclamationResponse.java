package com.pgi.pgic.dto;

import java.time.LocalDateTime;

public class ReclamationResponse {
    private Long id;
    private String numero;
    private String description;
    private boolean urgente;
    private String statut;
    private LocalDateTime dateCreation;

    public ReclamationResponse(Long id, String numero, String description, boolean urgente, String statut, LocalDateTime dateCreation) {
        this.id = id;
        this.numero = numero;
        this.description = description;
        this.urgente = urgente;
        this.statut = statut;
        this.dateCreation = dateCreation;
    }

    // Getters et setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public boolean isUrgente() { return urgente; }
    public void setUrgente(boolean urgente) { this.urgente = urgente; }
    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }
    public LocalDateTime getDateCreation() { return dateCreation; }
    public void setDateCreation(LocalDateTime dateCreation) { this.dateCreation = dateCreation; }
}