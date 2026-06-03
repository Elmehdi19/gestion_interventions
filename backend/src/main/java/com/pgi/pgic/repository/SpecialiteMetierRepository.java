package com.pgi.pgic.repository;

import com.pgi.pgic.entity.SpecialiteMetier;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SpecialiteMetierRepository extends JpaRepository<SpecialiteMetier, Long> {
    Optional<SpecialiteMetier> findByLibelle(String libelle);
}