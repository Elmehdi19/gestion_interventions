package com.pgi.pgic.repository;

import com.pgi.pgic.entity.Equipe;
import com.pgi.pgic.entity.Technicien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface TechnicienRepository extends JpaRepository<Technicien, Long> {
    List<Technicien> findByEquipe(Equipe equipe);

    List<Technicien> findByEquipeId(Long id);
    @Query("SELECT COUNT(i) FROM Intervention i WHERE i.technicien.id = :technicienId AND i.statut IN ('PRIS_EN_CHARGE', 'EN_COURS')")
    int countActiveInterventionsByTechnicienId(@Param("technicienId") Long technicienId);
}
