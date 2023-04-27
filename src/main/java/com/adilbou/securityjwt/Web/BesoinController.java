//package com.adilbou.securityjwt.Web;
//
//
//import com.adilbou.securityjwt.Entities.Besoin;
//import com.adilbou.securityjwt.Repositories.BesoinRepository;
//import com.adilbou.securityjwt.Repositories.MemberRepository;
//import com.adilbou.securityjwt.Repositories.RoleRepository;
//import com.adilbou.securityjwt.Repositories.UserRepository;
//import com.adilbou.securityjwt.Service.BesoinService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import java.sql.Date;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/v1/Besoin-Controller")
//
//@CrossOrigin("*")
//public class BesoinController {
//
//    private final BesoinService besoinService;
//
//    @Autowired
//    UserRepository userRepository;
//    @Autowired
//    RoleRepository roleRepository;
//
//
//    @Autowired
//    MemberRepository membreDepartementRepository;
//
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
//   // @PostMapping("")
//
//    @PostMapping("/save")
//    Besoin save(@RequestBody Besoin besoin) {
//        return besoinService.save(besoin);
//    }
//
//
//}
