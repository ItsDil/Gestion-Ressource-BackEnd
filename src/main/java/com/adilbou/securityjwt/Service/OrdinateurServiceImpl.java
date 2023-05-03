package com.adilbou.securityjwt.Service;//package com.adilbou.securityjwt.Service;


import com.adilbou.securityjwt.Entities.Ordinateur;
import com.adilbou.securityjwt.Repositories.OrdinateurRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//
//
//
//import com.adilbou.securityjwt.Entities.Ordinateur;
//import com.adilbou.securityjwt.Repositories.OrdinateurRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
@Service
public class OrdinateurServiceImpl implements OrdinateurService {

    private final OrdinateurRepository ordinateurRepository;

    public OrdinateurServiceImpl(OrdinateurRepository ordinateurRepository) {
        this.ordinateurRepository = ordinateurRepository;
    }
    //
    @Override
    public Ordinateur addOrdinateur(Ordinateur ordinateur) {
        return ordinateurRepository.save(ordinateur);
    }

    @Override
    public List<Ordinateur> getAllOrdinateurs() {
        List<Ordinateur> ordinateurs = ordinateurRepository.findAll();
        List<Ordinateur> ordinateurList = filterOrdinateurs(ordinateurs);
        return ordinateurList;
    }

    @Override
    public List<Ordinateur> getOrdinateursByMembreDepartement(Integer id) {

        return null;
    }

    @Override
    public List<Ordinateur> getOrdinateursByDepartement(String nameDepartement) {

        return null;
    }

    @Override
    public List<Ordinateur> getOrdinateursByFournisseur(Integer id) {
        return null;
    }

    @Override
    public List<Ordinateur> getOrdinateursByFournisseur(String id) {

        return null;
    }

    @Override
    public Ordinateur getOrdinateur(Long id) {
        Ordinateur ordinateur = ordinateurRepository.findById(id).orElseThrow(() ->
                new RuntimeException("L'ordinateur avec l'id = " + id + " est introuvable")
        );
        if(!ordinateur.getIsDeleted())
            return ordinateur;
        throw new RuntimeException("L'ordinateur avec l'id = " + id + " est introuvable");
    }

    @Override
    public Ordinateur updateOrdinateur(Ordinateur ordinateur) {
        return ordinateurRepository.save(ordinateur);
    }

    @Override
    public void deleteOrdinateur(Long id) {
        Ordinateur ordinateur = this.getOrdinateur(id);
        ordinateur.setIsDeleted(true);
        ordinateurRepository.save(ordinateur);
    }

    public List<Ordinateur> filterOrdinateurs(List<Ordinateur> ordinateurs) {
        List<Ordinateur> ordinateurList = new ArrayList<>();
        for(Ordinateur ordinateur: ordinateurs) {
            if(ordinateur.getType().equals("Ordinateur") && ordinateur.getIsDeleted() == false)
                ordinateurList.add(ordinateur);
        }
        return ordinateurList;
    }

}
