package com.adilbou.securityjwt.DTOs;

import lombok.Data;



@Data
public class PannesDTO {

    private Integer idPanne;
    private Integer idMember;
    private String firstname;
    private String email;
    private RessourcesDTO ressource;

    private String datePanne;
    private String constat;
    private String dateConstat;
    private Integer idFournisseur;
    private String firstnameFour;
    private String emailFour;
    private Boolean isThreated;

    private Boolean etat;
    public boolean getIsThreated(){
        return this.isThreated;
    }
    public void setIsThreated(boolean isThreated){
        this.isThreated=isThreated;
    }

}
