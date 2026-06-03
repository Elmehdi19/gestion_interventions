package com.pgi.pgic.repository;

import com.pgi.pgic.entity.TypeIntervention;
import com.pgi.pgic.entity.Famille;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TypeInterventionRepository extends JpaRepository<TypeIntervention, Long> {
    List<TypeIntervention> findByFamille(Famille famille);
     @Query("SELECT t FROM TypeIntervention t JOIN FETCH t.famille f JOIN FETCH f.specialite")
    List<TypeIntervention> findAllWithRelations();
}
