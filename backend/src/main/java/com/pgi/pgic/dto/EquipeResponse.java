package com.pgi.pgic.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.pgi.pgic.entity.Equipe;

import lombok.Data;

@Data

public class EquipeResponse {
    private Long id;
    private String nom;
    private String creneauHoraire;
    private String chefNom;
    private Long chefId;
    private String specialite;
    private int nbTechniciens;
    private List<TechnicienSimpleResponse> techniciens;
    
    private EquipeResponse toEquipeResponse(Equipe equipe) {
    EquipeResponse resp = new EquipeResponse();
    resp.setId(equipe.getId());
    resp.setNom(equipe.getNom());
    resp.setCreneauHoraire(equipe.getCreneauHoraire());
    resp.setChefId(equipe.getChef() != null ? equipe.getChef().getId() : null);
    resp.setChefNom(equipe.getChef() != null ? equipe.getChef().getPrenom() + " " + equipe.getChef().getNom() : null);
    resp.setSpecialite(equipe.getSpecialite() != null ? equipe.getSpecialite().name() : null);
    resp.setNbTechniciens(equipe.getTechniciens() != null ? equipe.getTechniciens().size() : 0);

    if (equipe.getTechniciens() != null) {
        List<TechnicienSimpleResponse> techniciens = equipe.getTechniciens().stream()
                .map(t -> new TechnicienSimpleResponse(t.getId(), t.getNom(), t.getPrenom()))
                .collect(Collectors.toList());
        resp.setTechniciens(techniciens);
    }
    return resp;
}
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

    public String getChefNom() {
        return chefNom;
    }

    public void setChefNom(String chefNom) {
        this.chefNom = chefNom;
    }

    public Long getChefId() {
        return chefId;
    }

    public void setChefId(Long chefId) {
        this.chefId = chefId;
    }

    public List<TechnicienSimpleResponse> getTechniciens() {
        return techniciens;
    }

    public void setTechniciens(List<TechnicienSimpleResponse> techniciens) {
        this.techniciens = techniciens;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public int getNbTechniciens() {
        return nbTechniciens;
    }

    public void setNbTechniciens(int nbTechniciens) {
        this.nbTechniciens = nbTechniciens;
    }

    public static class TechnicienSimpleResponse {
        private Long id;
        private String nom;
        private String prenom;
        public TechnicienSimpleResponse(Long id, String nom, String prenom) {
            this.id = id;
            this.nom = nom;
            this.prenom = prenom;}
            public Long getId() {
            return id;
        }
        public TechnicienSimpleResponse() {
        }
        public String getNom() {
            return nom;
        }
        public String getPrenom() {
            return prenom;
        }
        public void setId(Long id) {
            this.id = id;
        }
        public void setNom(String nom) {
            this.nom = nom;
        }
        public void setPrenom(String prenom) {
            this.prenom = prenom;
        }
    
    }
}