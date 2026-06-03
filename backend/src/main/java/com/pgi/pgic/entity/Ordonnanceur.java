// Ordonnanceur.java
package com.pgi.pgic.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@DiscriminatorValue("ORDONNANCEUR")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Ordonnanceur extends Utilisateur {
    @Enumerated(EnumType.STRING)
    private Specialite specialite;

    public Specialite getSpecialite() {
        return specialite;
    }

    public void setSpecialite(Specialite specialite) {
        this.specialite = specialite;
    }
}