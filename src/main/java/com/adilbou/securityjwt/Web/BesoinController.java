package com.adilbou.securityjwt.Web;

import com.adilbou.securityjwt.Entities.Besoin;
import com.adilbou.securityjwt.Service.BesoinService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/Besoin-Controller")
@CrossOrigin("*")
public class BesoinController {
    //
    private  BesoinService besoinService;



    public BesoinController(BesoinService besoinService) {
        this.besoinService = besoinService;
    }
    //    @Autowired
//    RoleRepository roleRepository;


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




}
