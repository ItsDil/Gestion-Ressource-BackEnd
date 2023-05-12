package com.adilbou.securityjwt.Service;

import com.adilbou.securityjwt.DTOs.*;
import com.adilbou.securityjwt.Entities.*;
import com.adilbou.securityjwt.Repositories.AppelOffreRepository;
import com.adilbou.securityjwt.Repositories.BesoinRepository;
import com.adilbou.securityjwt.Repositories.OffreRepository;
import com.adilbou.securityjwt.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AppelOffreServiceImpl implements AppelOffreService {

    private BesoinRepository besoinRepository;
    private AppelOffreRepository appelOffreRepository;
    private UserRepository userRepository;
    private OffreRepository offreRepository;

    public AppelOffreServiceImpl(BesoinRepository besoinRepository, AppelOffreRepository appelOffreRepository, UserRepository userRepository, OffreRepository offreRepository) {
        this.besoinRepository = besoinRepository;
        this.appelOffreRepository = appelOffreRepository;
        this.userRepository = userRepository;
        this.offreRepository = offreRepository;
    }

    public AppelOffre creationAppelOffre(AppelOffreDTO l) {
        AppelOffre newAppelOffre =new AppelOffre();
        List<Besoin> besoins=new ArrayList<>();
        List<BesoinAppOffDTO> besoinAppOffDTOS =l.getBesoinAppOffDTOS() ;
        for(BesoinAppOffDTO besoindto:besoinAppOffDTOS) {
            for (BesoinDTO besoin : besoindto.getBesoinDTO()) {
                Besoin b = besoinRepository.findBesoinById(besoin.getIdBesoin());
                besoins.add(b);
            }
        }


        newAppelOffre.setBesoins(besoins);
        //besoinRepository.findBesoinsNotInAppelOffre();
        newAppelOffre.setIsAffected(false);

        SimpleDateFormat dateFormates = new SimpleDateFormat("yyyy-MM-dd");
        String dateStringoffre = dateFormates.format(new Date());
        newAppelOffre.setDatePub(dateStringoffre);

        for(Besoin besoin:newAppelOffre.getBesoins()) {
            besoin.setIsBesoinInAppelOffre(true);
            besoinRepository.save(besoin);
        }
        AppelOffre savedAppelOffre = appelOffreRepository.save(newAppelOffre);
        return savedAppelOffre;
    }




    public List<ImprimanteGroup> findBesoingroupeMapped() {
        List<Object[]> queryResult = besoinRepository.findBesoingroupe();
        List<ImprimanteGroup> resultList = new ArrayList<>();
        for (Object[] result : queryResult) {
            ImprimanteGroup dto = new ImprimanteGroup(result[0].toString(), Integer.parseInt(result[1].toString()),  Integer.parseInt(result[2].toString()));
            resultList.add(dto);
        }
        return resultList;
    }
    //nadia adil

    public List<OrdinateurGroup> findBesoinOrdinateur(){
        List<Object[]> queryResult=besoinRepository.findBesoingroupeordinateur();
        List<OrdinateurGroup> resulist=new ArrayList<>();
        for (Object[] result : queryResult){
            OrdinateurGroup dto=new OrdinateurGroup(result[0].toString(),Integer.parseInt(result[1].toString()),result[2].toString(),result[3].toString(),Integer.parseInt(result[4].toString()));
            resulist.add(dto);
        }
        return resulist;
    }

    public AppelOffreDTOFour ShowFutureOffre_1(){
        String  nom_responsable="";
        AppelOffreDTOFour appelOffreDTOFour=new AppelOffreDTOFour();
        List<ImprimanteGroup> imprimanteGroupList=this.findBesoingroupeMapped();
        List<OrdinateurGroup> ordinateurGroupList=this.findBesoinOrdinateur();
        List<Object[]> queryResult=besoinRepository.findAllByIsBesoinInAppelOffreIsTrueAndvaliderIsTrueAndIsAffectedIsFalse();
        List<Long> idBesoins = new ArrayList<>();
        for (Object[] result : queryResult){
            idBesoins.add(Long.parseLong(result[0].toString()));
        }

        List<User> users=userRepository.findAll();
        for(User u:users){
            for(Role role:u.getRoles()){
                if (role.getRolename().equals("RESP"))
                    nom_responsable=u.getFirstname();
            }
        }
        appelOffreDTOFour.setFirstnameResp(nom_responsable);
        appelOffreDTOFour.setIdBesoins(idBesoins);
        appelOffreDTOFour.setOrdinateurGroups(ordinateurGroupList);
        appelOffreDTOFour.setImprimanteGroups(imprimanteGroupList);
        appelOffreDTOFour.setDateOffre(null);
        appelOffreDTOFour.setPrix(0.0);
        appelOffreDTOFour.setIdFournisseur(null);

        return appelOffreDTOFour;

    }



    public List<OrdinateurGroup> findBesoinOrdinateurOrd(Long id){
        List<Object[]> queryResult=besoinRepository.findBesoingroupeordinateurord(id);
        List<OrdinateurGroup> resulist=new ArrayList<>();
        for (Object[] result : queryResult){
            OrdinateurGroup dto=new OrdinateurGroup(result[0].toString(),Integer.parseInt(result[1].toString()),result[2].toString(),result[3].toString(),Integer.parseInt(result[4].toString()));
            resulist.add(dto);
        }
        return resulist;
    }

    public List<OrdinateurGroup> findBesoinOrdinateur(Long id){
        List<Object[]> queryResult=besoinRepository.findBesoingroupeordinateurbyid(id);
        List<OrdinateurGroup> resulist=new ArrayList<>();
        for (Object[] result : queryResult){
            OrdinateurGroup dto=new OrdinateurGroup(result[0].toString(),Integer.parseInt(result[1].toString()),result[2].toString(),result[3].toString(),Integer.parseInt(result[4].toString()));
            resulist.add(dto);
        }
        return resulist;
    }

    public List<ImprimanteGroup> findBesoingroupeMapped(Long id) {
        List<Object[]> queryResult = besoinRepository.findBesoingroupebyid(id);
        List<ImprimanteGroup> resultList = new ArrayList<>();
        for (Object[] result : queryResult) {
            ImprimanteGroup dto = new ImprimanteGroup(result[0].toString(), Integer.parseInt(result[1].toString()),  Integer.parseInt(result[2].toString()));
            resultList.add(dto);
        }
        return resultList;
    }


    public List<ImprimanteGroup> findBesoingroupeMappedimp(Long id) {
        List<Object[]> queryResult = besoinRepository.findBesoingroupeimp(id);
        List<ImprimanteGroup> resultList = new ArrayList<>();
        for (Object[] result : queryResult) {
            ImprimanteGroup dto = new ImprimanteGroup(result[0].toString(), Integer.parseInt(result[1].toString()),  Integer.parseInt(result[2].toString()));
            resultList.add(dto);
        }
        return resultList;
    }



//------------------------------------------------------------------------------------

    @Override
    public AppelOffreDTOFour ShowFutureOffre(Integer id) {

        List<Offre> offres=offreRepository.findOffreByIdFournisseur(id);
        List<AppelOffre> appelOffreList=appelOffreRepository.findAll();
        List<AppelOffre> appelOffreListnonAffected=new ArrayList<>();
        AppelOffreDTOFour appellfor=new AppelOffreDTOFour();
        String nom_respo="";
        List<User> users=userRepository.findAll();
        List<Ordinateur> ordinateurs=new ArrayList<>();
        List<Imprimante> imprimantes=new ArrayList<>();
        List<Ordinateur> ordinateursbesoin=new ArrayList<>();
        List<Imprimante> imprimantessbesoin=new ArrayList<>();
        for(User u:users){
            for(Role role:u.getRoles()){
                if (role.getRolename().equals("RESP"))
                    // afficher nom de responsable
                    nom_respo=u.getFirstname();
            }
        }
        for(AppelOffre app:appelOffreList){
            int flag=0;
            for(Offre of:offres){
                if(app.getId()==of.getAppelOffre().getId()){
                    flag=1;
                }


            }
            if(flag==0)
                appelOffreListnonAffected.add(app);
        }

        Long max=0l;
        for(AppelOffre appell:appelOffreListnonAffected){
            if(appell.getId()>max)
                max=appell.getId();
        }
        AppelOffre applast=appelOffreRepository.findAppelOffreById(max);
        appellfor.setFirstnameResp(nom_respo);
        appellfor.setIdBesoins(besoinRepository.findallbesoinAppelOffre(max));
        appellfor.setIdAppelOffre(max);

        for(Long i:besoinRepository.findallbesoinAppelOffre(max)){
            Besoin besoin=besoinRepository.findBesoinById(i);
            ordinateursbesoin= (List<Ordinateur>) besoin.getOrdinateurs();
            imprimantessbesoin= (List<Imprimante>) besoin.getImprimantes();

//            for(int j=0;j<besoin.getOrdinateurs().size();j++){
//                ordinateurs.add(ordinateursbesoin.get(j));
//
//            }
//            for(int j=0;j<besoin.getImprimantes().size();j++){
//                imprimantessbesoin.add(imprimantessbesoin.get(j));
//
//            }
            imprimantessbesoin.addAll(besoin.getImprimantes());
            ordinateursbesoin.addAll(besoin.getOrdinateurs());
            /////////////grouper ordinateurs//////////////


        }
        Map<String, Map<Integer, Map<String, Map<String, Long>>>> groupes = ordinateursbesoin.stream()
                .collect(Collectors.groupingBy(Ordinateur::getCpu,
                        Collectors.groupingBy(Ordinateur::getRam,
                                Collectors.groupingBy(Ordinateur::getStockage,
                                        Collectors.groupingBy(Ordinateur::getEcran,
                                                Collectors.counting())))));

        List<OrdinateurGroup> resultordinateur = groupes.entrySet().stream()
                .flatMap(cpuEntry -> cpuEntry.getValue().entrySet().stream()
                        .flatMap(ramEntry -> ramEntry.getValue().entrySet().stream()
                                .flatMap(disqueDurEntry -> disqueDurEntry.getValue().entrySet().stream()
                                        .map(ecranEntry -> new OrdinateurGroup(cpuEntry.getKey(),
                                                ramEntry.getKey(), disqueDurEntry.getKey(),
                                                ecranEntry.getKey(), Math.toIntExact(ecranEntry.getValue()))))))
                .collect(Collectors.toList());


        ////////////grouper imprimantes///////////////

        Map<String, Map<Integer, Long>> groupesImp = imprimantessbesoin.stream()
                .collect(Collectors.groupingBy(Imprimante::getResolution,
                        Collectors.groupingBy(Imprimante::getVitesse,
                                Collectors.counting())));

        List<ImprimanteGroup> resultimprimante = groupesImp.entrySet().stream()
                .flatMap(resolutionEntry -> resolutionEntry.getValue().entrySet().stream()
                        .map(vitesseImpressionEntry -> new ImprimanteGroup(resolutionEntry.getKey(),
                                vitesseImpressionEntry.getKey(), Math.toIntExact(vitesseImpressionEntry.getValue()))))
                .collect(Collectors.toList());

        appellfor.setOrdinateurGroups(this.findBesoinOrdinateurOrd(max));
        appellfor.setImprimanteGroups(this.findBesoingroupeMappedimp(max));

        return appellfor;
    }


    @Override
    public Offre saveOffre(AppelOffreDTOFour appell){
        Offre offre=new Offre();
        AppelOffre appelOffre1=appelOffreRepository.findAppelOffreById(appell.getIdAppelOffre());
        offre.setAppelOffre(appelOffre1);
        offre.setPrix(appell.getPrix());
        offre.setEtat(null);

        SimpleDateFormat dateFormates = new SimpleDateFormat("yyyy-MM-dd");
        String dateStringoffre = dateFormates.format(new java.util.Date());
        offre.setIdFournisseur(appell.getIdFournisseur());
        offre.setDate(dateStringoffre);
        offre.setIsAffected(Boolean.FALSE);
        return  offreRepository.save(offre);
    }


    @Override
    public AppleOffreDTOResp ShowAllAppelOffre_Offres() {
        List<AppelOffre> appelOffreList = appelOffreRepository.findAll();
        List<OffreDTOResp> offreDTORespList = new ArrayList<>();
        String nom_respo = "";
        for (AppelOffre app : appelOffreList) {
            OffreDTOResp dtoResp = new OffreDTOResp();
            List<AppelOffreDTOFour> appelOffreDTOFourList = new ArrayList<>();
            dtoResp.setIdAppelOffre(app.getId());
            SimpleDateFormat dateFormates = new SimpleDateFormat("yyyy-MM-dd");
            dtoResp.setDatePub(app.getDatePub());
            /// list offres

            List<Offre> offres = offreRepository.findByAppelOffre(app);
            for (Offre offre : offres) {
                if (offre.getIsAffected()) {
                    dtoResp.setAffected(true);
                    dtoResp.setIdFournisseurAffected(Math.toIntExact(offre.getIdFournisseur()));

                }
                AppelOffreDTOFour appelOffreDTOFour = new AppelOffreDTOFour();
                List<User> users = userRepository.findAll();

                for (User u : users) {
                    for (Role role : u.getRoles()) {
                        if (role.getRolename().equals("RESP"))
                            // afficher nom de responsable
                            nom_respo = u.getFirstname();
                    }
                }

                appelOffreDTOFour.setIdOffre(offre.getId());
                appelOffreDTOFour.setFirstnameResp(nom_respo);
                appelOffreDTOFour.setPrix(offre.getPrix());
                appelOffreDTOFour.setIdFournisseur(Math.toIntExact(offre.getIdFournisseur()));
                List<Long> list_id_besoin = besoinRepository.findallbesoinAppelOffre(app.getId());
                appelOffreDTOFour.setIdBesoins(list_id_besoin);
                List<OrdinateurGroup> ordinateurGroupList = new ArrayList<>();
                List<ImprimanteGroup> imprimanteGroupList = new ArrayList<>();
                for (Long i : list_id_besoin) {
//                    ImprimanteGroup imprimanteGroup = this.groupImpimanteByidBesoin(i);
//                    OrdinateurGroup ordinateurGroup = this.groupOrdinateurByidBesoin(i);
//                    ordinateurGroupList.add(ordinateurGroup);
//                    imprimanteGroupList.add(imprimanteGroup);
                }
                appelOffreDTOFour.setImprimanteGroups(this.findBesoingroupeMappedimp(app.getId()));
                appelOffreDTOFour.setOrdinateurGroups(this.findBesoinOrdinateurOrd(app.getId()));

                appelOffreDTOFourList.add(appelOffreDTOFour);
                dtoResp.setAppelOffreDTOFours(appelOffreDTOFourList);


            }
            offreDTORespList.add(dtoResp);
        }

        AppleOffreDTOResp appleOffreDTOResp = new AppleOffreDTOResp();
        appleOffreDTOResp.setOffreDTOResps(offreDTORespList);
        return appleOffreDTOResp;
    }




}




