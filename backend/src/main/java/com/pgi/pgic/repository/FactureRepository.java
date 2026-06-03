package com.pgi.pgic.repository;

import com.pgi.pgic.entity.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FactureRepository extends JpaRepository<Facture, Long> {
    List<Facture> findByClientIdOrderByDateEmissionDesc(Long clientId);
}