package com.pgi.pgic.repository;

import com.pgi.pgic.entity.Intervention;
import com.pgi.pgic.entity.Intervention.StatutIntervention;
import com.pgi.pgic.entity.Technicien;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InterventionRepository extends JpaRepository<Intervention, Long> {
    List<Intervention> findByStatut(StatutIntervention statut);
    List<Intervention> findByTechnicienAndStatut(Technicien technicien, StatutIntervention statut);
    List<Intervention> findByTechnicien(Technicien technicien);
    List<Intervention> findByStatutOrderByUrgenteDescDateCreationAsc(Intervention.StatutIntervention statut);
    List<Intervention> findByOrdonnanceurId(Long ordonnanceurId);
    List<Intervention> findByTechnicienIdAndStatutOrderByUrgenteDescDateCreationAsc(Long technicienId, Intervention.StatutIntervention statut);
    Optional<Intervention> findByIdAndTechnicienId(Long id, Long technicienId);
    long countByStatut(Intervention.StatutIntervention statut);
    long countByTechnicienId(Long technicienId);
    long countByTechnicienIdAndStatut(Long technicienId, Intervention.StatutIntervention statut);

    @Query("SELECT AVG(i.dureeEffectiveMinutes) FROM Intervention i WHERE i.technicien.id = :techId AND i.dureeEffectiveMinutes IS NOT NULL")
    Double getAverageDurationByTechnicien(@Param("techId") Long techId);

    // Méthode paginée correcte (déjà présente, à garder)
    @Query(value = "SELECT i FROM Intervention i " +
           "JOIN FETCH i.reclamation " +
           "JOIN FETCH i.typeIntervention " +
           "WHERE i.statut = :statut",
           countQuery = "SELECT COUNT(i) FROM Intervention i WHERE i.statut = :statut")
    Page<Intervention> findInterventionsByStatutWithFetch(
            @Param("statut") Intervention.StatutIntervention statut,
            Pageable pageable);

    // Compteur des interventions actives d'un technicien
    @Query("SELECT COUNT(i) FROM Intervention i WHERE i.technicien.id = :technicienId AND i.statut IN ('PRIS_EN_CHARGE', 'EN_COURS')")
    int countActiveInterventionsByTechnicienId(@Param("technicienId") Long id);
     List<Intervention> findByTechnicienIdAndStatut(Long technicienId, Intervention.StatutIntervention statut);
    List<Intervention> findByTechnicienId(Long technicienId);
 
}