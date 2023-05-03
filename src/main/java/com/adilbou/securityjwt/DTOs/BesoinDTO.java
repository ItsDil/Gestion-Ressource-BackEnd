package com.adilbou.securityjwt.DTOs;

import com.adilbou.securityjwt.Entities.Imprimante;
import com.adilbou.securityjwt.Entities.Ordinateur;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class BesoinDTO {


        private Integer id;
        private Long idBesoin;
        private String firstname;
        private String lastname;
        private String email;

        private String dateDemande;
        List<Ordinateur> ordinateurs=new ArrayList<>();
        List<Imprimante> Imprimantes=new ArrayList<>();
    }