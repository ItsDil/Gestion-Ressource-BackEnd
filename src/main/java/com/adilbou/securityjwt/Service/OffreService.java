package com.adilbou.securityjwt.Service;

import com.adilbou.securityjwt.DTOs.OffreDTO;

import java.util.List;

public interface OffreService {

    public List<OffreDTO> getOffresDTOsByFournisseur(Integer id);
    public void acceptOffre(Long idOffre);

    void affecterAppelOffer(long idOffre) ;

    void bannerFournisseur(Integer idFour);

}
