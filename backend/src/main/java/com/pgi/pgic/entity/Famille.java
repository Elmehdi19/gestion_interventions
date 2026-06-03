package com.pgi.pgic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "famille")
public class Famille {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String libelle;
    private String description;

    @ManyToOne
    @JoinColumn(name = "specialite_id")
    @JsonIgnore
    private SpecialiteMetier specialite;

    @OneToMany(mappedBy = "famille", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<TypeIntervention> types = new HashSet<>();

    // constructeurs, getters, setters
    public Famille() {}
    public Famille(String libelle, String description, SpecialiteMetier specialite) {
        this.libelle = libelle;
        this.description = description;
        this.specialite = specialite;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public SpecialiteMetier getSpecialite() { return specialite; }
    public void setSpecialite(SpecialiteMetier specialite) { this.specialite = specialite; }

    public Set<TypeIntervention> getTypes() { return types; }
    public void setTypes(Set<TypeIntervention> types) { this.types = types; }
}