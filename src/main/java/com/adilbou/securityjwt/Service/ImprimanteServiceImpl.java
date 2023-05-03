package com.adilbou.securityjwt.Service;//package com.adilbou.securityjwt.Service;


import com.adilbou.securityjwt.Entities.Imprimante;
import com.adilbou.securityjwt.Repositories.ImprimanteRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//
//
//import com.adilbou.securityjwt.Entities.Imprimante;
//import com.adilbou.securityjwt.Repositories.ImprimanteRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
@Service
public class ImprimanteServiceImpl implements ImprimanteService {

    private final ImprimanteRepository imprimanteRepository;
    public ImprimanteServiceImpl(ImprimanteRepository imprimanteRepository) {
        this.imprimanteRepository = imprimanteRepository;
    }

    @Override
    public Imprimante addImprimante(Imprimante imprimante) {
        return imprimanteRepository.save(imprimante);
    }

    @Override
    public List<Imprimante> getAllImprimantes() {
        List<Imprimante> imprimantes = imprimanteRepository.findAll();
        List<Imprimante> imprimanteList = filterImprimantes(imprimantes);
        return imprimanteList;
    }

    @Override
    public List<Imprimante> getImprimantesByMembreDepartement(Integer id) {

        return null;
    }

    @Override
    public List<Imprimante> getImprimantesByDepartementName(String name) {

        return null;
    }

    @Override
    public List<Imprimante> getImprimantesByFournisseur(Integer id) {
        return null;



    }


    @Override
    public Imprimante getImprimante(Long id) {
        Imprimante imprimante = imprimanteRepository.findById(id).orElseThrow(() ->
                new RuntimeException("L'imprimante avec l'id = " + id + " est introuvable")
        );
        if(!imprimante.getIsDeleted())
            return imprimante;
        throw new RuntimeException("L'imprimante avec l'id = " + id + " est introuvable");
    }

    @Override
    public Imprimante updateImprimante(Imprimante imprimante) {
        return imprimanteRepository.save(imprimante);
    }

    @Override
    public void deleteImprimante(Long id) {
        Imprimante imprimante = this.getImprimante(id);
        imprimante.setIsDeleted(true);
        imprimanteRepository.save(imprimante);
    }

    public List<Imprimante> filterImprimantes(List<Imprimante> imprimantes) {
        List<Imprimante> imprimanteList = new ArrayList<>();
        for(Imprimante imprimante: imprimantes) {
            if(imprimante.getType().equals("Imprimante") && imprimante.getIsDeleted() == false)
                imprimanteList.add(imprimante);
        }
        return imprimanteList;
    }

}
