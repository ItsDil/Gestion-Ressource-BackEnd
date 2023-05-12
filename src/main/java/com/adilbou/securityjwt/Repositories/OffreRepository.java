package com.adilbou.securityjwt.Repositories;

import com.adilbou.securityjwt.Entities.AppelOffre;
import com.adilbou.securityjwt.Entities.Message;
import com.adilbou.securityjwt.Entities.Offre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OffreRepository extends JpaRepository<Offre, Long> {

     List<Offre> findByAppelOffre(AppelOffre appelOffre);
//
////     Offre findByIdAppelOffreAndIdFournisseur(Long idApple, Long idFour);


     //     List<Offre> findOffreByIdAppelOffre(Long id);
     List<Offre> findOffreByIdFournisseur(Integer id);

     List<Offre> findAllByIdFournisseur(Integer id);

     Offre findOffreById(Long id);



}
