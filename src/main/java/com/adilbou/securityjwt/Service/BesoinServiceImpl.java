//package com.adilbou.securityjwt.Service;
//
//
//
//import com.adilbou.securityjwt.Entities.Besoin;
//import com.adilbou.securityjwt.Entities.Imprimante;
//import com.adilbou.securityjwt.Entities.Member;
//import com.adilbou.securityjwt.Entities.Ordinateur;
//import com.adilbou.securityjwt.Repositories.BesoinRepository;
//import com.adilbou.securityjwt.Repositories.MemberRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//@Service
//public class BesoinServiceImpl implements BesoinService {
//
//    private final BesoinRepository besoinRepository;
//    private final MemberRepository membreDepartementRepository;
////    private final RessourceService ressourceService;
//    private final OrdinateurService ordinateurService;
//    private final ImprimanteService imprimanteService;
//
//
//        public BesoinServiceImpl(BesoinRepository besoinRepository, MemberRepository membreDepartementRepository, OrdinateurService ordinateurService, ImprimanteService imprimanteService) {
//        this.besoinRepository = besoinRepository;
//        this.membreDepartementRepository = membreDepartementRepository;
////        this.ressourceService = ressourceService;
//        this.ordinateurService = ordinateurService;
//        this.imprimanteService = imprimanteService;
//    }
//// valider
//    @Override
//    public Besoin save(Besoin besoin) {
//        List<Ordinateur> ordinateurs = (List<Ordinateur>) besoin.getOrdinateurs();
//        List<Imprimante> imprimantes = (List<Imprimante>) besoin.getImprimantes();
//
//        List<Ordinateur> ordinateurList = new ArrayList<>();
//        List<Imprimante> imprimanteList = new ArrayList<>();
//
//        Member membreDepartement=membreDepartementRepository.findMembreDepartementById(besoin.getIdMembreDepartement());
//
//        String departementName = membreDepartement.getDepartement();
//
//        for(Ordinateur ordinateur: ordinateurs) {
//            Ordinateur ordTemp = ordinateurService.addOrdinateur(ordinateur);
//            ordTemp.setDeparementName(departementName);
//            ordTemp.setIdMembreDepartement(membreDepartement.getId());
//            ordinateurList.add(ordTemp);
//        }
//        for(Imprimante imprimante: imprimantes) {
//            Imprimante impTemp = imprimanteService.addImprimante(imprimante);
//            impTemp.setDeparementName(departementName);
//            impTemp.setIdMembreDepartement(membreDepartement.getId());
//            imprimanteList.add(impTemp);
//        }
//
////        ressourceService.addMultipleRessources(ressources);
//
//
//        besoin.setOrdinateurs(ordinateurList);
//        besoin.setImprimantes(imprimanteList);
//        besoin.setDepartementName(departementName);
//        return besoinRepository.save(besoin);
//    }
//    @Override
//    public List<Besoin> getAllBesoins() {
//        return besoinRepository.findAll();
//    }
//    @Override
//    public List<Besoin> getAllBesoinDepartement(String nameDepartement) {
//        return besoinRepository.findBesoinByDepartementName(nameDepartement);
//    }
//    @Override
//    public List<Besoin> getBesoinsMembreDepartement(Integer id) {
//        return besoinRepository.findBesoinByIdMembreDepartement(id);
//    }
//    @Override
//    public List<Besoin> getBesoinsDepartementNotInAppelOffre(String nameDepartement) {
//        return besoinRepository.findBesoinByDepartementNameAndIsBesoinInAppelOffreIsFalse(nameDepartement);
//    }
//    @Override
//    public Besoin updateBesoin(Besoin besoin) {
//        Member membreDepartement=membreDepartementRepository.findMembreDepartementById(besoin.getIdMembreDepartement());
//
//        String departementNam = membreDepartement.getDepartement();
//        besoin.setDepartementName(departementNam);
//        return besoinRepository.save(besoin);
//    }
//    @Override
//    public Besoin getBesoinMembreDepartementNotInAppelOffre(Integer id) {
//        return besoinRepository.findBesoinByIdMembreDepartementAndIsBesoinInAppelOffreIsFalse(id);
//    }
//    @Override
//    public void besoinAddedInAppelOffre(Long id) {
//        Besoin besoin = besoinRepository.findById(id).orElseThrow(() ->
//            new RuntimeException("Le besoin avec l'id = " + id + " est introuvable")
//        );
//        besoin.setIsBesoinInAppelOffre(true);
//        besoinRepository.save(besoin);
//    }
//
//    @Override
//    public List<Besoin> getBesoinsNotInAppelOffre() {
//        return besoinRepository.findAllByIsBesoinInAppelOffreIsFalse();
//    }
//
//    @Override
//    public void deleteBesoinOfMembre(Integer id) {
//        List<Besoin> besoins=besoinRepository.findBesoinByIdMembreDepartementAndIsAffectedIsFalse(id);
//        besoinRepository.deleteAll(besoins);
//    }
//    @Override
//    public void deleteBesoin(Long id) {
//        besoinRepository.deleteById(id);
//    }
//
//
//
//}
