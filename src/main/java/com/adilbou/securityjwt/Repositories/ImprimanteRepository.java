package com.adilbou.securityjwt.Repositories;



import com.adilbou.securityjwt.Entities.Imprimante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
@Repository
public interface ImprimanteRepository extends JpaRepository<Imprimante, Long> {
//
//    public List<Imprimante> getImprimanteByIdMembreDepartement(Integer id);
//    public List<Imprimante> getImprimanteByDeparementName(String DeparementName);
////    public List<Imprimante> getImprimanteByIdFournisseur(String id);


    Imprimante findImprimanteById(Long id);
}
