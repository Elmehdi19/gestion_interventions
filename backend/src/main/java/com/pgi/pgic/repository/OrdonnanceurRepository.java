package com.pgi.pgic.repository;  
import com.pgi.pgic.entity.Ordonnanceur;
import com.pgi.pgic.entity.Role;
import com.pgi.pgic.entity.Specialite;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrdonnanceurRepository extends JpaRepository<Ordonnanceur, Long> {
    List<Ordonnanceur> findByRoleAndSpecialite(Role role, Specialite specialite);
}