package com.adilbou.securityjwt.DTOs;

import com.adilbou.securityjwt.Entities.Ressource;
import jakarta.persistence.Column;

public class PannesDTO {
    private Integer idMember;
    private String firstname;
    private String email;
    private Ressource ressource;
    private String dateDemande;

}
