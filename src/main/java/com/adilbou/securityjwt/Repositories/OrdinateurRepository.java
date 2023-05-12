package com.adilbou.securityjwt.Repositories;



import com.adilbou.securityjwt.Entities.Ordinateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdinateurRepository extends JpaRepository<Ordinateur, Long> {
//
//    public List<Ordinateur> getOrdinateurByIdMembreDepartement(Integer id);
//    public List<Ordinateur> getOrdinateurByDeparementName(String id);
////    public List<Ordinateur> getOrdinateurByIdFournisseur(Integer id);
    public Ordinateur findOrdinateurById(Long id);

}
