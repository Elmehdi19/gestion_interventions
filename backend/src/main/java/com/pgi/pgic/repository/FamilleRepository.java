package com.pgi.pgic.repository;
import com.pgi.pgic.entity.Famille;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.pgi.pgic.entity.SpecialiteMetier;
public interface FamilleRepository extends JpaRepository<Famille, Long> {
    List<Famille> findBySpecialite(SpecialiteMetier specialite);
}

