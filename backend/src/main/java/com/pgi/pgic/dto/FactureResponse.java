package com.pgi.pgic.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class FactureResponse {
    private Long id;
    private String numeroFacture;
    private BigDecimal montantTTC;
    private LocalDate dateEmission;
    private LocalDate dateEcheance;
    private String statut;
    private String pdfUrl;
    private Integer periodeMois;
    private Integer periodeAnnee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroFacture() {
        return numeroFacture;
    }

    public void setNumeroFacture(String numeroFacture) {
        this.numeroFacture = numeroFacture;
    }

    public BigDecimal getMontantTTC() {
        return montantTTC;
    }

    public void setMontantTTC(BigDecimal montantTTC) {
        this.montantTTC = montantTTC;
    }

    public LocalDate getDateEmission() {
        return dateEmission;
    }

    public void setDateEmission(LocalDate dateEmission) {
        this.dateEmission = dateEmission;
    }

    public LocalDate getDateEcheance() {
        return dateEcheance;
    }

    public void setDateEcheance(LocalDate dateEcheance) {
        this.dateEcheance = dateEcheance;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }

    public Integer getPeriodeMois() {
        return periodeMois;
    }

    public void setPeriodeMois(Integer periodeMois) {
        this.periodeMois = periodeMois;
    }

    public Integer getPeriodeAnnee() {
        return periodeAnnee;
    }

    public void setPeriodeAnnee(Integer periodeAnnee) {
        this.periodeAnnee = periodeAnnee;
    }
}