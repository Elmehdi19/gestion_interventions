// ChefEquipe.java
package com.pgi.pgic.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("CHEF_EQUIPE")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ChefEquipe extends Utilisateur {
    @OneToOne
@JoinColumn(name = "equipe_id")
private Equipe equipe;

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }
}