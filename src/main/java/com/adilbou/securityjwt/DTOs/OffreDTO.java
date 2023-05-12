package com.adilbou.securityjwt.DTOs;

import com.adilbou.securityjwt.Enumration.ETAT_OFFRE;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class OffreDTO {
    private Long id;
    private Boolean isAffected;
    private ETAT_OFFRE etat; // ACCEPTED , REJETED , BLOCKED
    private String firstnameFour;
}