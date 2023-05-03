package com.adilbou.securityjwt.Web;

import com.adilbou.securityjwt.DTOs.AppelOffreDTO;
import com.adilbou.securityjwt.DTOs.BesoinValide;
import com.adilbou.securityjwt.DTOs.MiniAppelOffreDTO;
import com.adilbou.securityjwt.Entities.Besoin;
import com.adilbou.securityjwt.Entities.Imprimante;
import com.adilbou.securityjwt.Entities.Member;
import com.adilbou.securityjwt.Entities.Ordinateur;
import com.adilbou.securityjwt.Repositories.MemberRepository;
import com.adilbou.securityjwt.Repositories.UserRepository;
import com.adilbou.securityjwt.Service.BesoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/Besoin-Controller")
@CrossOrigin("*")
public class BesoinController {
    //
    private final BesoinService besoinService;
    //
    @Autowired
    UserRepository userRepository;

    public BesoinController(BesoinService besoinService) {
        this.besoinService = besoinService;
    }
    //    @Autowired
//    RoleRepository roleRepository;

    @Autowired
    MemberRepository membreDepartementRepository;
//    @Autowired
//    BesoinRepository besoinRepository;
//
//
//
//    public BesoinController(BesoinService besoinService) {
//        this.besoinService = besoinService;
//    }
//
////    @GetMapping("")
////    public List<Besoin> getAllBesoins() {
////        return besoinService.getAllBesoins();
////    }
//


    @PostMapping("/save")
    Besoin save(@RequestBody Besoin besoin) {
        return besoinService.save(besoin);
    }

    @GetMapping("/appel-offre/{id}")
    public MiniAppelOffreDTO getMiniAppelOffre(@PathVariable Integer id){
        return besoinService.getMiniAppelOffre(id);
    }

    @PostMapping("/appel-offre-validation")
    public void MiniAppelOffreValidation( @RequestBody BesoinValide besoinValide){
        besoinService.MiniAppelOffreValidation(besoinValide);
    }
    @GetMapping("/appel-offre-valide")
    public AppelOffreDTO getAppelOffreValide(){
        return besoinService.getAppelOffreValide();
    }


}
