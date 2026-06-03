// Technicien.java
package com.pgi.pgic.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("TECHNICIEN")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Technicien extends Utilisateur {
        @ManyToOne
    @JoinColumn(name = "equipe_id")
    private Equipe equipe;

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

  }