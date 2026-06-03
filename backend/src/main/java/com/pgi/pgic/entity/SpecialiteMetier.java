package com.pgi.pgic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "specialite_metier")
public class SpecialiteMetier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String libelle;

    @OneToMany(mappedBy = "specialite", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Famille> familles = new HashSet<>();

    // constructeurs, getters, setters
    public SpecialiteMetier() {}
    public SpecialiteMetier(String libelle) { this.libelle = libelle; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }

    public Set<Famille> getFamilles() { return familles; }
    public void setFamilles(Set<Famille> familles) { this.familles = familles; }
}