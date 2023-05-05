package com.adilbou.securityjwt.DTOs;

import java.util.ArrayList;
import java.util.List;

public class AppelOffreDTOFour {

    private String firstnameResp;
    private Integer idFournisseur;
    private String dateOffre;
    private double prix;
    private List<Long> idBesoins = new ArrayList<>();
    private List<ImprimanteGroup> imprimanteGroups = new ArrayList<>();
    private List<OrdinateurGroup> ordinateurGroups = new ArrayList<>();

}
