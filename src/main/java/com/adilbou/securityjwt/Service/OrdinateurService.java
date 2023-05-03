package com.adilbou.securityjwt.Service;//package com.adilbou.securityjwt.Service;


import com.adilbou.securityjwt.Entities.Ordinateur;

import java.util.List;

//
//
//
//import com.adilbou.securityjwt.Entities.Ordinateur;
//
//import java.util.List;
//
public interface OrdinateurService {

    Ordinateur addOrdinateur(Ordinateur ordinateur);
    List<Ordinateur> getAllOrdinateurs();
    List<Ordinateur> getOrdinateursByMembreDepartement(Integer id);
    List<Ordinateur> getOrdinateursByDepartement(String nameDepartement);
    List<Ordinateur> getOrdinateursByFournisseur(Integer id);

    List<Ordinateur> getOrdinateursByFournisseur(String id);

    Ordinateur getOrdinateur(Long id);
    Ordinateur updateOrdinateur(Ordinateur ordinateur);

    void deleteOrdinateur(Long id);

}
