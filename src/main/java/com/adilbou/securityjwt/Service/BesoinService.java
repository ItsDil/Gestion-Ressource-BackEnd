package com.adilbou.securityjwt.Service;




import com.adilbou.securityjwt.DTOs.AppelOffreDTO;
import com.adilbou.securityjwt.DTOs.BesoinDTO;
import com.adilbou.securityjwt.DTOs.BesoinValide;
import com.adilbou.securityjwt.DTOs.MiniAppelOffreDTO;
import com.adilbou.securityjwt.Entities.Besoin;

import java.util.List;
//
public interface BesoinService {
//
    Besoin save(Besoin besoin);
//    List<Besoin> getAllBesoins();
//    List<Besoin> getAllBesoinDepartement(String nameDepartement);
//    List<Besoin> getBesoinsMembreDepartement(Integer id);
//    void deleteBesoinOfMembre(Integer id);
//    void deleteBesoin(Long id);
//    List<Besoin> getBesoinsDepartementNotInAppelOffre(String nameDepartement);
//    Besoin updateBesoin(Besoin besoin);
//    Besoin getBesoinMembreDepartementNotInAppelOffre(Integer id);
//    void besoinAddedInAppelOffre(Long id);
//    List<Besoin> getBesoinsNotInAppelOffre();
//


    MiniAppelOffreDTO getMiniAppelOffre(Integer id);
    void MiniAppelOffreValidation(BesoinValide besoinValide);

    AppelOffreDTO getAppelOffreValide();
}
