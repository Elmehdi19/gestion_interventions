package com.pgi.pgic.dto;

import java.time.LocalDateTime;

public class InterventionResponse {
    private Long id;
    private String numero;
    private boolean urgente;
    private String statut;
    private LocalDateTime dateCreation;
    private Long reclamationId;
    private String reclamationNumero;     // Nouveau : numéro de la réclamation
    private Long typeInterventionId;      // Optionnel : ID du type d'intervention
    private String typeLibelle;           // Nouveau : libellé du type d'intervention
    private Long chefEquipeId;
    private String chefEquipeNom;
    public InterventionResponse() {}
    // Constructeur complet (utilisez-le dans vos contrôleurs)
    public InterventionResponse(Long id, String numero, boolean urgente, String statut, LocalDateTime dateCreation,
                                Long reclamationId, String reclamationNumero,
                                Long typeInterventionId, String typeLibelle,
                                Long chefEquipeId, String chefEquipeNom) {
        this.id = id;
        this.numero = numero;
        this.urgente = urgente;
        this.statut = statut;
        this.dateCreation = dateCreation;
        this.reclamationId = reclamationId;
        this.reclamationNumero = reclamationNumero;
        this.typeInterventionId = typeInterventionId;
        this.typeLibelle = typeLibelle;
        this.chefEquipeId = chefEquipeId;
        this.chefEquipeNom = chefEquipeNom;
    }

    // Getters et setters pour les nouveaux champs
    public String getReclamationNumero() { return reclamationNumero; }
    public void setReclamationNumero(String reclamationNumero) { this.reclamationNumero = reclamationNumero; }

    public Long getTypeInterventionId() { return typeInterventionId; }
    public void setTypeInterventionId(Long typeInterventionId) { this.typeInterventionId = typeInterventionId; }

    public String getTypeLibelle() { return typeLibelle; }
    public void setTypeLibelle(String typeLibelle) { this.typeLibelle = typeLibelle; }

    // Getters et setters existants
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public boolean isUrgente() { return urgente; }
    public void setUrgente(boolean urgente) { this.urgente = urgente; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }

    public LocalDateTime getDateCreation() { return dateCreation; }
    public void setDateCreation(LocalDateTime dateCreation) { this.dateCreation = dateCreation; }

    public Long getReclamationId() { return reclamationId; }
    public void setReclamationId(Long reclamationId) { this.reclamationId = reclamationId; }

    public Long getChefEquipeId() { return chefEquipeId; }
    public void setChefEquipeId(Long chefEquipeId) { this.chefEquipeId = chefEquipeId; }

    public String getChefEquipeNom() { return chefEquipeNom; }
    public void setChefEquipeNom(String chefEquipeNom) { this.chefEquipeNom = chefEquipeNom; }
}