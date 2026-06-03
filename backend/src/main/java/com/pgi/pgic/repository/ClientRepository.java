package com.pgi.pgic.repository;

import com.pgi.pgic.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByNumeroContrat(String numeroContrat);
    Optional<Client> findByEmail(String email);
}