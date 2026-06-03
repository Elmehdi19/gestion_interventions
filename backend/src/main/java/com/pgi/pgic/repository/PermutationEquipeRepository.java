package com.pgi.pgic.repository;

import com.pgi.pgic.entity.PermutationEquipe;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PermutationEquipeRepository extends JpaRepository<PermutationEquipe, Long> {
    List<PermutationEquipe> findByStatut(String statut);
    List<PermutationEquipe> findByTechnicienId(Long technicienId);
}
