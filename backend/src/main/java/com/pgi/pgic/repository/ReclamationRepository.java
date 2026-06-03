package com.pgi.pgic.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.pgi.pgic.entity.Reclamation;
import com.pgi.pgic.entity.Reclamation.StatutReclamation;
import com.pgi.pgic.entity.Specialite;

import java.util.Optional;
import com.pgi.pgic.entity.Client;
public interface ReclamationRepository extends JpaRepository<Reclamation, Long> {
    Reclamation save(Reclamation reclamation);
    List<Reclamation> findByClientId(Long clientId);
    Optional<Reclamation> findByNumero(String numero);
    List<Reclamation> findByClient(Client client);
    List<Reclamation> findByClientOrderByDateCreationDesc(Client client);
    List<Reclamation> findByStatutAndClient(Reclamation.StatutReclamation statut, Client client);
    List<Reclamation> findByStatutAndSpecialiteConcerneeOrderByUrgenteDescDateCreationAsc(
    Reclamation.StatutReclamation statut, 
    Specialite specialiteConcernee);
    long countByStatut(Reclamation.StatutReclamation statut);
    List<Reclamation> findByStatutAndSpecialiteConcerneeAndInterventionIsNull(
    StatutReclamation statut, Specialite specialite);
}
