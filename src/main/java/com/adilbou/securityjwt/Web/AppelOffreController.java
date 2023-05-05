package com.adilbou.securityjwt.Web;


import com.adilbou.securityjwt.DTOs.AppelOffreDTO;
import com.adilbou.securityjwt.DTOs.BesoinValide;
import com.adilbou.securityjwt.DTOs.MiniAppelOffreDTO;
import com.adilbou.securityjwt.Service.BesoinService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class AppelOffreController {

    private  BesoinService besoinService;

    public AppelOffreController(BesoinService besoinService) {
        this.besoinService = besoinService;
    }


    @GetMapping("/AppelOffre-Controller/appel-offre-valide")
    public AppelOffreDTO getAppelOffreValide(){
        return besoinService.getAppelOffreValide();
    }

    @GetMapping("/Besoin-Controller/appel-offre/{id}")
    public MiniAppelOffreDTO getMiniAppelOffre(@PathVariable Integer id){
        return besoinService.getMiniAppelOffre(id);
    }

    @PostMapping("/Besoin-Controller/appel-offre-validation")
    public void MiniAppelOffreValidation( @RequestBody BesoinValide besoinValide){
        besoinService.MiniAppelOffreValidation(besoinValide);
    }

}
