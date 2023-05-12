package com.adilbou.securityjwt.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OffreDTOResp {

    private Long  idAppelOffre;
    private String datePub;
    private boolean isAffected;
    private Integer idFournisseurAffected;
    private List<AppelOffreDTOFour> appelOffreDTOFours= new ArrayList<>();

}
