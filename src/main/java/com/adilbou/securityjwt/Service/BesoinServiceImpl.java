package com.adilbou.securityjwt.Service;//package com.adilbou.securityjwt.Service;


import com.adilbou.securityjwt.DTOs.*;
import com.adilbou.securityjwt.Entities.*;
import com.adilbou.securityjwt.Repositories.BesoinRepository;
import com.adilbou.securityjwt.Repositories.MemberRepository;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BesoinServiceImpl implements BesoinService {

    private final BesoinRepository besoinRepository;
    private final MemberRepository membreDepartementRepository;

    private final OrdinateurService ordinateurService;
    private final ImprimanteService imprimanteService;


    public BesoinServiceImpl(BesoinRepository besoinRepository, MemberRepository membreDepartementRepository, OrdinateurService ordinateurService, ImprimanteService imprimanteService) {
        this.besoinRepository = besoinRepository;
        this.membreDepartementRepository = membreDepartementRepository;

        this.ordinateurService = ordinateurService;
        this.imprimanteService = imprimanteService;
    }

    //// valider
    @Override
    public Besoin save(Besoin besoin) {
        List<Ordinateur> ordinateurs = (List<Ordinateur>) besoin.getOrdinateurs();
        List<Imprimante> imprimantes = (List<Imprimante>) besoin.getImprimantes();

        List<Ordinateur> ordinateurList = new ArrayList<>();
        List<Imprimante> imprimanteList = new ArrayList<>();

        Member membreDepartement = membreDepartementRepository.findById(besoin.getIdMembreDepartement()).orElseThrow(()->new RuntimeException(""));

        String departementName = membreDepartement.getDepartement();

        for (Ordinateur ordinateur : ordinateurs) {

            Ordinateur ordTemp = new Ordinateur();
            ordTemp.setCpu(ordinateur.getCpu());
            ordTemp.setRam(ordinateur.getRam());
            ordTemp.setStockage(ordinateur.getStockage());
            ordTemp.setEcran(ordinateur.getEcran());
            ordTemp.setDeparementName(departementName);
            ordTemp.setIdMembreDepartement(membreDepartement.getId());
            ordinateurList.add(ordTemp);
            ordinateurService.addOrdinateur(ordTemp);
        }
        for (Imprimante imprimante : imprimantes) {
            Imprimante impTemp = new Imprimante();
            impTemp.setResolution(imprimante.getResolution());
            impTemp.setVitesse(imprimante.getVitesse());
            impTemp.setDeparementName(departementName);
            impTemp.setIdMembreDepartement(membreDepartement.getId());
            imprimanteList.add(impTemp);
            imprimanteService.addImprimante(impTemp);
        }


//        @Override
//        public List<BesoinDTO> getBesoin_department(Integer id) {
//            List<BesoinDTO> miniAppels=new ArrayList<>();
//            Member membreDepartement = membreDepartementRepository.findMembreDepartementById(id);
//
//            String departementName = membreDepartement.getDepartement();
//
//            List<Member> members=membreDepartementRepository.findMembreDepartementBydepartement(departementName);
//            List<Besoin> besoinList=besoinRepository.findByDepartementNameAndIsAffectedIsFalseAndIsBesoinInAppelOffreIsFalse(departementName);
//            for(Member member:members){
//                for(Besoin besoin:besoinList){
//                    mini_appel mini=new mini_appel();
//                    mini.setFirstname(member.getFirstname());
//                    mini.setLastname(member.getLastname());
//                    mini.setEmail(member.getEmail());
//                    mini.setDateDemande(besoin.getDateDemande());
//                    mini.setOrdinateurs((List<Ordinateur>) besoin.getOrdinateurs());
//                    mini.setImprimantes((List<Imprimante>) besoin.getImprimantes());
//                    miniAppels.add(mini);
//                }
//            }
//
//            return miniAppels;
//        }
//


        besoin.setIdMembreDepartement(membreDepartement.getId());

        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = date.format(formatter);

        besoin.setDateDemande(formattedDate);
        besoin.setOrdinateurs(ordinateurList);
        besoin.setImprimantes(imprimanteList);
        besoin.setDepartementName(departementName);
        return besoinRepository.save(besoin);
    }

    @Override
    public MiniAppelOffreDTO getMiniAppelOffre(Integer id) {

        MiniAppelOffreDTO miniAppelOffreDTO = new MiniAppelOffreDTO();
        List<BesoinDTO> besoinDTO=new ArrayList<>();

        Member membreDepartement = membreDepartementRepository.findMembreDepartementById(id);

        String departementName = membreDepartement.getDepartement();

        List<Member> members=membreDepartementRepository.findMembreDepartementBydepartement(departementName);
        List<Besoin> besoinList=besoinRepository.findByDepartementNameAndIsAffectedIsFalseAndIsBesoinInAppelOffreIsFalse(departementName);

        for(Member member:members){
            BesoinDTO miniBesoin=new BesoinDTO();

            for(Besoin besoin:besoinList){
                if(!member.getId().equals(id) && member.getId().equals( besoin.getIdMembreDepartement())) {
                    miniBesoin.setId(member.getId());
                    miniBesoin.setIdBesoin(besoin.getId());
                    miniBesoin.setFirstname(member.getFirstname());
                    miniBesoin.setLastname(member.getLastname());
                    miniBesoin.setEmail(member.getEmail());
                    miniBesoin.setDateDemande(besoin.getDateDemande());
                    miniBesoin.setOrdinateurs((List<Ordinateur>) besoin.getOrdinateurs());
                    miniBesoin.setImprimantes((List<Imprimante>) besoin.getImprimantes());
                }
            }
            if(!member.getId().equals(id)) {
                besoinDTO.add(miniBesoin);
            }

        }

        miniAppelOffreDTO.setBesoinDTO(besoinDTO);

        return miniAppelOffreDTO;
    }

    @Override
    public void MiniAppelOffreValidation(BesoinValide besoinValide) {
        for (Long i : besoinValide.getBesoinChecked()) {
            Besoin b = besoinRepository.findById(i).orElse(null);
            if(b !=null){
                b.setValider(true);
                besoinRepository.save(b);
            }

        }
    }

    @Override
    public AppelOffreDTO getAppelOffreValide() {
        AppelOffreDTO appelOffreDTO = new AppelOffreDTO();
        List<BesoinAppOffDTO> besoinAppOffDTOS = new ArrayList<>();

        List<Besoin> besoins=new ArrayList<>();
//        besoins=besoinRepository.findBesoinByIsAffectedIsFalseAndValiderIsTrue();
        List<Member> Members=membreDepartementRepository.findAll();
        List<String> departement= new ArrayList<>();
        List<Member> ChefDeparts= new ArrayList<>();
        for(Besoin b:besoins){
            if (!departement.contains(b.getDepartementName())) {
                departement.add(b.getDepartementName());
            }
        }


        for(Member m:Members) {
            if (m.getRoles().get(0).getRolename().equals("CHDP")) {

                besoins=besoinRepository.findBesoinByDepartementNameAndIsAffectedIsFalseAndIsBesoinInAppelOffreIsNullAndValiderIsTrue(m.getDepartement());
                if(besoins != null){

                    BesoinAppOffDTO besoinAppOffDTO = new BesoinAppOffDTO();
                    besoinAppOffDTO.setFirstname(m.getFirstname());
                    besoinAppOffDTO.setEmail(m.getEmail());
                    besoinAppOffDTO.setDepartement(m.getDepartement());

                    List<BesoinDTO> besoinDTOS = getMiniAppelOffre(m.getId()).getBesoinDTO();

                    besoinAppOffDTO.setBesoinDTO(besoinDTOS);

                    besoinAppOffDTOS.add(besoinAppOffDTO);
                }
            }
        }

        appelOffreDTO.setBesoinAppOffDTOS(besoinAppOffDTOS);

        return appelOffreDTO;
    }
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
}

