package com.pgi.pgic.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Equipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;          // "Matin", "Nuit"
    private String creneauHoraire; // "08:00-20:00", "20:00-08:00"
    @OneToMany(mappedBy = "equipe")
    private List<Technicien> techniciens = new ArrayList<>();

   @Enumerated(EnumType.STRING)
    private Specialite specialite;
    @ManyToOne
    private ChefEquipe chef;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCreneauHoraire() {
        return creneauHoraire;
    }

    public void setCreneauHoraire(String creneauHoraire) {
        this.creneauHoraire = creneauHoraire;
    }

    public List<Technicien> getTechniciens() {
        return techniciens;
    }

    public void setTechniciens(List<Technicien> techniciens) {
        this.techniciens = techniciens;
    }

    public ChefEquipe getChef() {
        return chef;
    }

    public void setChef(ChefEquipe chef) {
        this.chef = chef;
    }
    public Specialite getSpecialite() {
        return specialite;
    }

    public void setSpecialite(Specialite specialite) {
        this.specialite = specialite;
    }
}