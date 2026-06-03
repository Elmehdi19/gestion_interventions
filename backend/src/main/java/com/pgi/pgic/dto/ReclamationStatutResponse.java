package com.pgi.pgic.dto;

import java.time.LocalDateTime;

public class ReclamationStatutResponse {
    private String numero;
    private String statut;
    private String description;
    private LocalDateTime dateCreation;
    private String interventionNumero; // si intervention créée

    public ReclamationStatutResponse(String numero, String statut, String description, LocalDateTime dateCreation, String interventionNumero) {
        this.numero = numero;
        this.statut = statut;
        this.description = description;
        this.dateCreation = dateCreation;
        this.interventionNumero = interventionNumero;
    }

    // Getters et setters
    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDateTime getDateCreation() { return dateCreation; }
    public void setDateCreation(LocalDateTime dateCreation) { this.dateCreation = dateCreation; }
    public String getInterventionNumero() { return interventionNumero; }
    public void setInterventionNumero(String interventionNumero) { this.interventionNumero = interventionNumero; }
}