package com.adilbou.securityjwt.Repositories;

import com.adilbou.securityjwt.Entities.Ressource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RessourceRepository extends JpaRepository<Ressource,Long> {
	 Ressource findRessourceById(Long id);
    List<Ressource> findByIdMembreDepartement(Integer idMembreDepartement);
    List<Ressource> findByIdMembreDepartementAndIdFournisseurIsNotNull(Integer idMembreDepartement);



}
