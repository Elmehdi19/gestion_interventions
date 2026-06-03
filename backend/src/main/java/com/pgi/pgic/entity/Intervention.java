package com.pgi.pgic.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "intervention")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Intervention {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String numero;

    private boolean urgente;
    private String rapport;
    private Integer dureeEffectiveMinutes;

    @Enumerated(EnumType.STRING)
    private StatutIntervention statut;

    private LocalDateTime dateCreation;
    private LocalDateTime datePriseEnCharge;
    private LocalDateTime dateDebut;
    private LocalDateTime dateEmpechement;
    private LocalDateTime dateCloture;

    @OneToOne
    @JoinColumn(name = "reclamation_id")
    private Reclamation reclamation;

    @ManyToOne
    @JoinColumn(name = "technicien_id")
    private Technicien technicien;

    @ManyToOne
    @JoinColumn(name = "ordonnanceur_id")
    private Ordonnanceur ordonnanceur;

    @ManyToOne
    @JoinColumn(name = "type_intervention_id")
    private TypeIntervention typeIntervention;

    @ManyToOne
    @JoinColumn(name ="chef_equipe_id")
    private ChefEquipe chefEquipe;
    
    public ChefEquipe getChefEquipe() {
        return chefEquipe;
    }
    public void setChefEquipe(ChefEquipe chefEquipe) {
        this.chefEquipe = chefEquipe;
    }
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

    public boolean isUrgente() {
        return urgente;
    }

    public void setUrgente(boolean urgente) {
        this.urgente = urgente;
    }

    public String getRapport() {
        return rapport;
    }

    public void setRapport(String rapport) {
        this.rapport = rapport;
    }

    public Integer getDureeEffectiveMinutes() {
        return dureeEffectiveMinutes;
    }

    public void setDureeEffectiveMinutes(Integer dureeEffectiveMinutes) {
        this.dureeEffectiveMinutes = dureeEffectiveMinutes;
    }

    public StatutIntervention getStatut() {
        return statut;
    }

    public void setStatut(StatutIntervention statut) {
        this.statut = statut;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDateTime getDatePriseEnCharge() {
        return datePriseEnCharge;
    }

    public void setDatePriseEnCharge(LocalDateTime datePriseEnCharge) {
        this.datePriseEnCharge = datePriseEnCharge;
    }

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDateTime getDateEmpechement() {
        return dateEmpechement;
    }

    public void setDateEmpechement(LocalDateTime dateEmpechement) {
        this.dateEmpechement = dateEmpechement;
    }

    public LocalDateTime getDateCloture() {
        return dateCloture;
    }

    public void setDateCloture(LocalDateTime dateCloture) {
        this.dateCloture = dateCloture;
    }

    public Reclamation getReclamation() {
        return reclamation;
    }

    public void setReclamation(Reclamation reclamation) {
        this.reclamation = reclamation;
    }

    public Technicien getTechnicien() {
        return technicien;
    }

    public void setTechnicien(Technicien technicien) {
        this.technicien = technicien;
    }

    public Ordonnanceur getOrdonnanceur() {
        return ordonnanceur;
    }

    public void setOrdonnanceur(Ordonnanceur ordonnanceur) {
        this.ordonnanceur = ordonnanceur;
    }

    public TypeIntervention getTypeIntervention() {
        return typeIntervention;
    }

    public void setTypeIntervention(TypeIntervention typeIntervention) {
        this.typeIntervention = typeIntervention;
    }

    public enum StatutIntervention {
        EN_ATTENTE, PRIS_EN_CHARGE, EN_COURS, EMPECHE, CLOTURE
    }
}