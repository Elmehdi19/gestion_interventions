package com.pgi.pgic.entity;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reclamation")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reclamation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String numero;

    private String description;
    private boolean urgente;
    private String piecesJointes;

    @Enumerated(EnumType.STRING)
    private StatutReclamation statut;

    private LocalDateTime dateCreation = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToOne(mappedBy = "reclamation", cascade = CascadeType.ALL,
              fetch = FetchType.LAZY, optional = true)
    private Intervention intervention;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public StatutReclamation getStatut() {
        return statut;
    }

    public void setStatut(StatutReclamation statut) {
        this.statut = statut;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Intervention getIntervention() {
        return intervention;
    }

    public void setIntervention(Intervention intervention) {
        this.intervention = intervention;
    }

    public Ordonnanceur getOrdonnanceur() {
        return ordonnanceur;
    }

    public void setOrdonnanceur(Ordonnanceur ordonnanceur) {
        this.ordonnanceur = ordonnanceur;
    }

    public Specialite getSpecialiteConcernee() {
        return specialiteConcernee;
    }

    public void setSpecialiteConcernee(Specialite specialiteConcernee) {
        this.specialiteConcernee = specialiteConcernee;
    }

    public enum StatutReclamation {
        NOUVELLE, QUALIFIEE, INTERVENTION_CREE, CLOTUREE
    }
    @ManyToOne
    @JoinColumn(name = "ordonnanceur_id")
    private Ordonnanceur ordonnanceur;
    @Enumerated(EnumType.STRING)
    private Specialite specialiteConcernee;
    }