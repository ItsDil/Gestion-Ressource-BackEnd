package com.adilbou.securityjwt.Repositories;

import com.adilbou.securityjwt.Entities.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FournisseurRepository extends JpaRepository<Fournisseur,Integer> {

    List<Fournisseur> findAll();



}
