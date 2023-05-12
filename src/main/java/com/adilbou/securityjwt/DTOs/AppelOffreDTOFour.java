package com.adilbou.securityjwt.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppelOffreDTOFour {

    private Long idOffre;
    private String firstnameResp;
    private Integer idFournisseur;
    private String dateOffre;
    private double prix;
    private Long idAppelOffre;
    private List<Long> idBesoins = new ArrayList<>();
    private List<ImprimanteGroup> imprimanteGroups = new ArrayList<>();
    private List<OrdinateurGroup> ordinateurGroups = new ArrayList<>();



}
