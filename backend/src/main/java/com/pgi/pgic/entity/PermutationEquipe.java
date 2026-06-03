package com.pgi.pgic.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermutationEquipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private String motif;
    private String typePermutation; // "IMMEDIATE", "DIFFEREE", "TEMPORAIRE"
    private String statut;          // "EN_ATTENTE", "VALIDEE", "REJETEE"

    @ManyToOne
    private Equipe equipeDestination; // L'équipe cible

    @ManyToOne
    private Technicien technicien;

    @ManyToOne
    private ChefEquipe demandeur;     // Chef qui a demandé

    private LocalDateTime dateDemande = LocalDateTime.now();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Equipe getEquipeDestination() {
        return equipeDestination;
    }

    public void setEquipeDestination(Equipe equipeDestination) {
        this.equipeDestination = equipeDestination;
    }

    public Technicien getTechnicien() {
        return technicien;
    }

    public void setTechnicien(Technicien technicien) {
        this.technicien = technicien;
    }

    public ChefEquipe getDemandeur() {
        return demandeur;
    }

    public void setDemandeur(ChefEquipe demandeur) {
        this.demandeur = demandeur;
    }

    public LocalDateTime getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(LocalDateTime dateDemande) {
        this.dateDemande = dateDemande;
    }
}