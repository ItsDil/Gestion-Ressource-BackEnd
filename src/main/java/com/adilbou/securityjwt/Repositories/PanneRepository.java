package com.adilbou.securityjwt.Repositories;

import com.adilbou.securityjwt.Entities.Panne;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PanneRepository extends JpaRepository<Panne, Long> {
    Panne findPanneById(Long id);
    Panne findByIdRessource(Long idRessource);
    List<Panne> findByidTechnicien(Integer idTechnicien);
    List<Panne> findAllByConstatIsNotNullAndIsTreatedIsTrueAndEtatIsTrue();
}