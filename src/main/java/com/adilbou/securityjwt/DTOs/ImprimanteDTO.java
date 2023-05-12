package com.adilbou.securityjwt.DTOs;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ImprimanteDTO extends RessourcesDTO{
    private String resolution;
    private int vitesse;

    public ImprimanteDTO(Long id,String codeBarre,String dateFinGarantie,
                         String resolution,int vitesse)
    {
        super(id,codeBarre,dateFinGarantie);
        this.resolution=resolution;
        this.vitesse=vitesse;

//        super(id,codeBarre,dateFinGarantie);
    }


}
