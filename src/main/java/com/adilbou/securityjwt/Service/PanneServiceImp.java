package com.adilbou.securityjwt.Service;


import com.adilbou.securityjwt.DTOs.*;
import com.adilbou.securityjwt.Entities.*;
import com.adilbou.securityjwt.Repositories.*;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class PanneServiceImp implements PanneService{

	private final PanneRepository panneRepository;
	private final RessourceRepository ressourceRepository;
	private final MemberRepository membreDepartementRepository;
	private final BesoinRepository besoinRepository;
	private final FournisseurRepository fournisseurRepository;
	private final ImprimanteRepository imprimanteRepository;
	private final OrdinateurRepository ordinateurRepository;

	private final TechnicienRepository technicienRepository;

	public PanneServiceImp(PanneRepository panneRepository, RessourceRepository ressourceRepository, MemberRepository membreDepartementRepository, BesoinRepository besoinRepository, FournisseurRepository fournisseurRepository, ImprimanteRepository imprimanteRepository, OrdinateurRepository ordinateurRepository, TechnicienRepository technicienRepository) {
		this.panneRepository = panneRepository;
		this.ressourceRepository = ressourceRepository;
		this.membreDepartementRepository = membreDepartementRepository;
		this.besoinRepository = besoinRepository;
		this.fournisseurRepository = fournisseurRepository;
		this.imprimanteRepository = imprimanteRepository;
		this.ordinateurRepository = ordinateurRepository;
		this.technicienRepository = technicienRepository;
	}

	@Override
	public List<Panne> getAllPannes() {
		return panneRepository.findAll();
	}


	@Override
	public void setConstatPanne(PanneDTO_Constat panneConstatDTO) {
		Long idPanne= panneConstatDTO.getIdPanne();

		Panne panne=panneRepository.findPanneById(idPanne);

		panne.setConstat(panneConstatDTO.getConstat());

		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = formatter.format(date);
		panne.setDateConstat(formattedDate);
		panne.setIsTreated(true);
		panneRepository.save(panne);
	}

	@Override
	public void setEtat(Long idPanne) {
		Panne panne=panneRepository.getById(idPanne);
		panne.setEtat(!panne.getEtat());
		panne.setIsTreated(!panne.getIsTreated());
		panneRepository.save(panne);
	}

	@Override
	public List<Panne> getAllPannesForTec() {
		List<Technicien> techniciens = technicienRepository.findAll();

		Integer idTech = techniciens.get(0).getId();
		return panneRepository.findByidTechnicien(idTech);
	}

	@Override
	public List<Panne> getAllPannesForResp() {
		return panneRepository.findAllByConstatIsNotNullAndIsTreatedIsTrueAndEtatIsTrue();
	}

	@Override
	public void setPanne(PanneDTO_Ens panneDTOEns) {

		if(panneRepository.findByIdRessource(panneDTOEns.getIdRessource())!= null) {

			Panne panne=panneRepository.findByIdRessource(panneDTOEns.getIdRessource());
			panne.setEtat(!panne.getEtat());
//			panne.setIsTreated(!panne.getIsTreated());
			List<Technicien> techniciens = technicienRepository.findAll();
			Integer idTech = techniciens.get(0).getId();
			Integer idTechnicien=panne.getIdTechnicien();
			if(idTechnicien != null) {
				panne.setIdTechnicien(null);
			} else panne.setIdTechnicien(idTech);
			panneRepository.save(panne);

		} else {
			Panne p = new Panne();
			p.setIdMembreDepartement(panneDTOEns.getIdMembreDepartement());
			p.setIdRessource(panneDTOEns.getIdRessource());
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String formattedDate = formatter.format(date);
			p.setDateApparition(formattedDate);
			p.setIsTreated(false);
			p.setEtat(true);

			List<Technicien> techniciens = technicienRepository.findAll();
			Integer idTech = techniciens.get(0).getId();
			Integer idTechnicien=p.getIdTechnicien();
			if(idTechnicien != null) {
				p.setIdTechnicien(null);
			} else p.setIdTechnicien(idTech);

			panneRepository.save(p);
		}
	}

	@Override
	public RessourceDTO_ENS getRessourcesWithState(Integer idEnse){


		List<Ressource> ressources= ressourceRepository.findByIdMembreDepartementAndIdFournisseurIsNotNull(idEnse);

		RessourceDTO_ENS ressourceDTOEns=new RessourceDTO_ENS();
		int i=0;
		for(Ressource r:ressources) {

			RessourcesDTO ressourcesDTO= new RessourcesDTO();
			ressourcesDTO.setId(r.getId());
			ressourcesDTO.setCodeBarre(r.getCodeBarre());
			ressourcesDTO.setDateDemande(r.getDateDemande());


			ImprOrd imprOrd = new ImprOrd();

			if(r instanceof Imprimante){
				ressourcesDTO.setTypeRessource("Imprimante");
				imprOrd.setVitesse(((Imprimante) r).getVitesse());
				imprOrd.setResolution(((Imprimante) r).getResolution());
			}else{
				ressourcesDTO.setTypeRessource("Ordinateur");
				imprOrd.setStockage(((Ordinateur) r).getStockage());
				imprOrd.setRam(((Ordinateur) r).getRam());
				imprOrd.setCpu(((Ordinateur) r).getCpu());
				imprOrd.setEcran(((Ordinateur) r).getEcran());
			}

			ressourcesDTO.setImprOrd(imprOrd);

			if(panneRepository.findByIdRessource(ressourcesDTO.getId()) != null && panneRepository.findByIdRessource(ressourcesDTO.getId()).getIsTreated()){
				ressourcesDTO.setEtat(true);
				ressourcesDTO.setIsTraited(true);
			} else if(panneRepository.findByIdRessource(ressourcesDTO.getId()) != null && !panneRepository.findByIdRessource(ressourcesDTO.getId()).getIsTreated()){
				ressourcesDTO.setEtat(true);
				ressourcesDTO.setIsTraited(false);
			} else {
				ressourcesDTO.setEtat(false);
				ressourcesDTO.setIsTraited(false);
			}
			ressourceDTOEns.getRessourcesDTOList().add(ressourcesDTO);
		}


		return ressourceDTOEns;

	}


	//--------------------------------------------------------------------


	@Override
	public PanneDTO_Tech panneTechnicien() {
		int cpt=0;
		boolean found=false;
		PanneDTO_Tech panneTech=new PanneDTO_Tech();
		List<Panne> pannes=this.getAllPannesForTec();
		PanneDTO_Dep panneDTO_Dep=new PanneDTO_Dep();
		for(Panne p:pannes) {
			Integer idMember=p.getIdMembreDepartement();
			String depName=membreDepartementRepository.findMembreDepartementById(idMember).getDepartement();
			RessourcesDTO ressourcesDTO=new RessourcesDTO();
			for(int i=0;i<cpt;i++) {
				panneDTO_Dep=panneTech.getPanneDTODeps().get(i);
				String depNameDtp=panneDTO_Dep.getDepartementName();
				if(depNameDtp.equals(depName)) {
					found = true;
					break;
				}
			}

			if(!found) {
				System.out.println("234");
				panneDTO_Dep = new PanneDTO_Dep();
				panneDTO_Dep.setDepartementName(depName);
				panneTech.getPanneDTODeps().add(panneDTO_Dep);
				cpt++;
			}
			Member membreDepartement=membreDepartementRepository.findMembreDepartementById(p.getIdMembreDepartement());
			PannesDTO panneDTO=new PannesDTO();
			panneDTO.setDatePanne(p.getDateApparition());
			panneDTO.setIsThreated(p.getIsTreated());
			panneDTO.setIdMember(p.getIdMembreDepartement());
			panneDTO.setEmail(membreDepartement.getEmail());
			panneDTO.setFirstname(membreDepartement.getFirstname());
			panneDTO.setIdPanne(Math.toIntExact(p.getId()));
			panneDTO.setEtat(p.getEtat());
			if(ressourceRepository.findRessourceById(p.getIdRessource()) instanceof Imprimante){
				Imprimante ip=imprimanteRepository.findImprimanteById(
						ressourceRepository.findRessourceById(p.getIdRessource()).getId()
				);
				ressourcesDTO=new ImprimanteDTO(ip.getId(),ip.getCodeBarre(),ip.getDateFinGarantie()
						,ip.getResolution(),ip.getVitesse()

				);
			} else if(ressourceRepository.findRessourceById(p.getIdRessource()) instanceof Ordinateur){
				Ordinateur or=ordinateurRepository.findOrdinateurById(
						ressourceRepository.findRessourceById(p.getIdRessource()).getId()
				);
				ressourcesDTO=new OrdinateurDTO(or.getId(),or.getCodeBarre(),or.getDateFinGarantie()
						,or.getCpu(),or.getRam(),or.getStockage(),or.getEcran()
				);
			} else ressourcesDTO=null;

			panneDTO.setRessource(ressourcesDTO);
			panneDTO_Dep.getPannes().add(panneDTO);


			found=false;
		}
		return panneTech;
	}

	@Override
	public PanneDTO_Tech panneResponsable() {
		int cpt=0;
		boolean found=false;
		PanneDTO_Tech panneTech=new PanneDTO_Tech();
		List<Panne> pannes=this.getAllPannesForResp();
		PanneDTO_Dep panneDTO_Dep=new PanneDTO_Dep();
		for(Panne p:pannes) {

			Integer idMember=p.getIdMembreDepartement();
			String depName=membreDepartementRepository.findMembreDepartementById(idMember).getDepartement();
			RessourcesDTO ressourcesDTO=new RessourcesDTO();
			for(int i=0;i<cpt;i++) {
				panneDTO_Dep=panneTech.getPanneDTODeps().get(i);
				String depNameDtp=panneDTO_Dep.getDepartementName();
				if(depNameDtp.equals(depName)) {
					found = true;
					break;
				}
			}

			if(!found) {
				System.out.println("234");
				panneDTO_Dep = new PanneDTO_Dep();
				panneDTO_Dep.setDepartementName(depName);
				panneTech.getPanneDTODeps().add(panneDTO_Dep);
				cpt++;
			}
			Member membreDepartement=membreDepartementRepository.findMembreDepartementById(p.getIdMembreDepartement());
			Fournisseur fournisseur=fournisseurRepository
					.findById(ressourceRepository.findRessourceById(p.getIdRessource()).getIdFournisseur()).orElse(null);

			System.out.println(fournisseur);

			PannesDTO panneDTO=new PannesDTO();
			if( fournisseur!=null){

				panneDTO.setFirstnameFour(fournisseur.getFirstname());
				panneDTO.setIdFournisseur(fournisseur.getId());
				panneDTO.setEmailFour(fournisseur.getEmail());


			}

			panneDTO.setDatePanne(p.getDateApparition());
			panneDTO.setIdPanne(Math.toIntExact(p.getId()));
			panneDTO.setIsThreated(p.getIsTreated());
			panneDTO.setIdMember(p.getIdMembreDepartement());
			panneDTO.setEmail(membreDepartement.getEmail());
			panneDTO.setConstat(p.getConstat());
			panneDTO.setEtat(p.getEtat());
			panneDTO.setFirstname(membreDepartement.getFirstname());
			panneDTO.setEmailFour(fournisseur.getEmail());
			panneDTO.setDateConstat(p.getDateConstat());
			panneDTO.setFirstnameFour(fournisseur.getFirstname());
			panneDTO.setIdFournisseur(fournisseur.getId());
			if(ressourceRepository.findRessourceById(p.getIdRessource()) instanceof Imprimante){
				Imprimante ip=imprimanteRepository.findImprimanteById(
						ressourceRepository.findRessourceById(p.getIdRessource()).getId()
				);
				ressourcesDTO=new ImprimanteDTO(ip.getId(),ip.getCodeBarre(),ip.getDateFinGarantie()
						,ip.getResolution(),ip.getVitesse()

				);
			} else if(ressourceRepository.findRessourceById(p.getIdRessource()) instanceof Ordinateur){
				Ordinateur or=ordinateurRepository.findOrdinateurById(
						ressourceRepository.findRessourceById(p.getIdRessource()).getId()
				);
				ressourcesDTO=new OrdinateurDTO(or.getId(),or.getCodeBarre(),or.getDateFinGarantie()
						,or.getCpu(),or.getRam(),or.getStockage(),or.getEcran()
				);
			} else ressourcesDTO=null;

			panneDTO.setRessource(ressourcesDTO);
			panneDTO_Dep.getPannes().add(panneDTO);
			found=false;
		}
		return panneTech;
	}



}