package com.pgi.pgic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "type_intervention")
public class TypeIntervention {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String libelle;
    private Integer dureeEstimeeMinutes;

    @ManyToOne
    @JoinColumn(name = "famille_id")
    @JsonIgnore
    private Famille famille;

    // constructeurs, getters, setters
    public TypeIntervention() {}
    public TypeIntervention(String libelle, Integer dureeEstimeeMinutes, Famille famille) {
        this.libelle = libelle;
        this.dureeEstimeeMinutes = dureeEstimeeMinutes;
        this.famille = famille;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }

    public Integer getDureeEstimeeMinutes() { return dureeEstimeeMinutes; }
    public void setDureeEstimeeMinutes(Integer dureeEstimeeMinutes) { this.dureeEstimeeMinutes = dureeEstimeeMinutes; }

    public Famille getFamille() { return famille; }
    public void setFamille(Famille famille) { this.famille = famille; }
}