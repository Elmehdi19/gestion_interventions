package com.pgi.pgic.repository;

import com.pgi.pgic.entity.Role;
import com.pgi.pgic.entity.Specialite;
import com.pgi.pgic.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    Optional<Utilisateur> findByEmail(String email);
    List<Utilisateur> findByRole(Role role);
}