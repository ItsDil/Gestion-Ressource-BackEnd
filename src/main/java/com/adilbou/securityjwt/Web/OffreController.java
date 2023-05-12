package com.adilbou.securityjwt.Web;


import com.adilbou.securityjwt.DTOs.OffreDTO;
import com.adilbou.securityjwt.Service.AppelOffreService;
import com.adilbou.securityjwt.Service.BesoinService;
import com.adilbou.securityjwt.Service.OffreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class OffreController {

    private OffreService offreService;


    public OffreController(OffreService offreService) {
        this.offreService = offreService;
    }

    @GetMapping("/Offre-Controller/Offres/{idFour}")
    public List<OffreDTO> getAllOffresFour(@PathVariable Integer idFour){
        return offreService.getOffresDTOsByFournisseur(idFour);
    }

    @GetMapping("/Offre-Controller/accept/{idOffre}")
    public void acceptOffre(@PathVariable Long idOffre){
         offreService.acceptOffre(idOffre);
    }

    @GetMapping("/Offre-Controller/affectAppelOffre/{idOffre}")
    public void affecterAppelOffer(@PathVariable Long idOffre){
        offreService.affecterAppelOffer(idOffre);
    }

    @GetMapping("/Offre-Controller/bannerFour/{idFour}")
    public void setInBlackList(@PathVariable Integer idFour){
        offreService.bannerFournisseur(idFour);
    }



}
