package com.adilbou.securityjwt.Service;


import com.adilbou.securityjwt.DTOs.CodeBarre;
import com.adilbou.securityjwt.DTOs.OffreDTO;
import com.adilbou.securityjwt.Entities.*;
import com.adilbou.securityjwt.Enumration.ETAT_OFFRE;
import com.adilbou.securityjwt.Repositories.*;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OffreServiceImpl implements OffreService{


    private BesoinRepository besoinRepository;
    private AppelOffreRepository appelOffreRepository;
    private UserRepository userRepository;
    private OffreRepository offreRepository;

    private OrdinateurRepository ordinateurRepository;
    private ImprimanteRepository imprimanteRepository;

    private FournisseurRepository fournisseurRepository;

    public OffreServiceImpl(BesoinRepository besoinRepository, AppelOffreRepository appelOffreRepository, UserRepository userRepository, OffreRepository offreRepository, OrdinateurRepository ordinateurRepository, ImprimanteRepository imprimanteRepository, FournisseurRepository fournisseurRepository) {
        this.besoinRepository = besoinRepository;
        this.appelOffreRepository = appelOffreRepository;
        this.userRepository = userRepository;
        this.offreRepository = offreRepository;
        this.ordinateurRepository = ordinateurRepository;
        this.imprimanteRepository = imprimanteRepository;
        this.fournisseurRepository = fournisseurRepository;
    }

    public List<OffreDTO> getOffresDTOsByFournisseur(Integer id) {

        List<OffreDTO> offreDTOList=new ArrayList<>();
        List<Offre> listOffres = offreRepository.findAllByIdFournisseur(id);
        Fournisseur fournisseur= fournisseurRepository.findById(id).orElse(null);


        for (Offre offre : listOffres) {
            OffreDTO offreDTO = new OffreDTO();
            offreDTO.setId(offre.getId());
            offreDTO.setEtat(offre.getEtat());
            offreDTO.setIsAffected(offre.getIsAffected());
            if(fournisseur!=null) {
                offreDTO.setFirstnameFour(fournisseur.getFirstname());
            }
            offreDTOList.add(offreDTO);
        }
        return offreDTOList;
    }

    @Override
    public void acceptOffre(Long idOffre) {

        Offre offre = offreRepository.findOffreById(idOffre);
        if(offre!=null){
            offre.setEtat(ETAT_OFFRE.ACCEPTED);
            offre.setIsAffected(true);
            offreRepository.save(offre);

            Integer idFournisseur = offre.getIdFournisseur();

            AppelOffre appelOffre = offre.getAppelOffre();

            List<Offre> offres = offreRepository.findByAppelOffre(appelOffre);

            for(Offre off : offres){

                if(!off.getIdFournisseur().equals(idFournisseur)){
                    off.setEtat(ETAT_OFFRE.REJECTED);
                    offreRepository.save(off);
                }

            }

        }

    }
    @Override
    public void affecterAppelOffer(long idOffre) {
        Offre offre=offreRepository.findOffreById(idOffre);
        AppelOffre appelOffre=appelOffreRepository.findAppelOffreById(offre.getAppelOffre().getId());
        List<Besoin> besoins=(List<Besoin>) appelOffre.getBesoins();

        for(Besoin besoin:besoins){

            besoin.setIsAffected(Boolean.TRUE);
            SimpleDateFormat dateFormates = new SimpleDateFormat("yyyy-MM-dd");
            String dateAffectation = dateFormates.format(new Date());
            besoin.setDateAffectation(dateAffectation);


            List<Ordinateur> ordinateurs = (List<Ordinateur>) besoin.getOrdinateurs();
            List<Imprimante> imprimantes = (List<Imprimante>) besoin.getImprimantes();


            LocalDate today = LocalDate.now(); // Obtenez la date actuelle
            LocalDate datePlusOneYear = today.plusYears(1); // Ajoutez un an à la date
            Date dateGarantie = Date.from(datePlusOneYear.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()); // Convertissez la LocalDate en Date
            String dateG=dateFormates.format(dateGarantie);

            for (Ordinateur ordinateur : ordinateurs) {
                String randomString = CodeBarre.generateRandomString(10);
                ordinateur.setCodeBarre(randomString);
                ordinateur.setDateFinGarantie(dateG);
                ordinateur.setIdFournisseur(offre.getIdFournisseur());
                ordinateurRepository.save(ordinateur);

            }
            for (Imprimante imprimante : imprimantes) {
                String randomString = CodeBarre.generateRandomString(10);
                imprimante.setCodeBarre(randomString);
                imprimante.setDateFinGarantie(dateG);
                imprimante.setIdFournisseur(offre.getIdFournisseur());
                imprimanteRepository.save(imprimante);
            }

            besoinRepository.save(besoin);

        }


    }

    @Override
    public void bannerFournisseur(Integer idFour) {

        Fournisseur fournisseur = fournisseurRepository.findById(idFour).orElse(null);
        if(fournisseur !=null){

            fournisseur.setInBlackList(!fournisseur.isInBlackList());

            fournisseurRepository.save(fournisseur);
        }

    }


}
