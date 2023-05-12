package com.adilbou.securityjwt.Service;

import com.adilbou.securityjwt.DTOs.AppelOffreDTO;
import com.adilbou.securityjwt.DTOs.AppelOffreDTOFour;
import com.adilbou.securityjwt.DTOs.AppleOffreDTOResp;
import com.adilbou.securityjwt.Entities.AppelOffre;
import com.adilbou.securityjwt.Entities.Offre;

public interface AppelOffreService {


    public AppelOffre creationAppelOffre(AppelOffreDTO l);

    public AppelOffreDTOFour ShowFutureOffre_1();

    public AppleOffreDTOResp ShowAllAppelOffre_Offres();

    public AppelOffreDTOFour ShowFutureOffre(Integer id);

    public Offre saveOffre(AppelOffreDTOFour appell);
}
