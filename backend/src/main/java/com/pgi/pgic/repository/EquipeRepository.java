package com.pgi.pgic.repository;

import com.pgi.pgic.entity.Equipe;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EquipeRepository extends JpaRepository<Equipe, Long> {
    Optional<Equipe> findByNom(String nom);
}