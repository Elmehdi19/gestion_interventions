package com.pgi.pgic.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data

public class PermutationResponse {
    private Long id;
    private Long technicienId;
    private String technicienNom;
    private Long equipeDestinationId;
    private String equipeDestinationNom;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private String motif;
    private String typePermutation;
    private String statut;
    private LocalDateTime dateDemande;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTechnicienId() {
        return technicienId;
    }

    public void setTechnicienId(Long technicienId) {
        this.technicienId = technicienId;
    }

    public String getTechnicienNom() {
        return technicienNom;
    }

    public void setTechnicienNom(String technicienNom) {
        this.technicienNom = technicienNom;
    }

    public Long getEquipeDestinationId() {
        return equipeDestinationId;
    }

    public void setEquipeDestinationId(Long equipeDestinationId) {
        this.equipeDestinationId = equipeDestinationId;
    }

    public String getEquipeDestinationNom() {
        return equipeDestinationNom;
    }

    public void setEquipeDestinationNom(String equipeDestinationNom) {
        this.equipeDestinationNom = equipeDestinationNom;
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

    public LocalDateTime getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(LocalDateTime dateDemande) {
        this.dateDemande = dateDemande;
    }
  
}