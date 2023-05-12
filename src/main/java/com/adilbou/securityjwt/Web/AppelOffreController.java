package com.adilbou.securityjwt.Web;


import com.adilbou.securityjwt.DTOs.*;
import com.adilbou.securityjwt.Entities.AppelOffre;
import com.adilbou.securityjwt.Entities.Offre;
import com.adilbou.securityjwt.Service.AppelOffreService;
import com.adilbou.securityjwt.Service.BesoinService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class AppelOffreController {

    private  BesoinService besoinService;
    private AppelOffreService appelOffreService;

    public AppelOffreController(BesoinService besoinService, AppelOffreService appelOffreService) {
        this.besoinService = besoinService;
        this.appelOffreService = appelOffreService;
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

    @PostMapping("/AppelOffre-Controller/save")
    public AppelOffre CreateAppelOffre(@RequestBody AppelOffreDTO appelOffreDTO){
        return appelOffreService.creationAppelOffre(appelOffreDTO);
    }


    @GetMapping("/AppelOffre-Controller/ShowAppelOffre")
    public AppelOffreDTOFour ShowFutureOffre_1(){
        return appelOffreService.ShowFutureOffre_1();
    }



    @GetMapping("/AppelOffre-Controller/ShowAppelOffre/{idFour}")
    public AppelOffreDTOFour ShowFutureOffre(@PathVariable Integer idFour){
        return appelOffreService.ShowFutureOffre(idFour);
    }


    @PostMapping("/AppelOffre-Controller/saveOffre")
    public Offre CreateOffre(@RequestBody AppelOffreDTOFour appelOffreDTOFour){
        return appelOffreService.saveOffre(appelOffreDTOFour);
    }

    @GetMapping("/AppelOffre-Controller/ShowOffres")
    public AppleOffreDTOResp ShowAllAppelOffre_Offres(){
        return appelOffreService.ShowAllAppelOffre_Offres();
    }
}
