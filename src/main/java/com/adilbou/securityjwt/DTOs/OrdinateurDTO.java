package com.adilbou.securityjwt.DTOs;


import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class OrdinateurDTO extends RessourcesDTO{
    private String cpu;
    private int ram;
    private String stockage;
    private String ecran;
    public OrdinateurDTO(Long id, String codeBarre, String  dateFinGarantie,
                         String cpu, int ram,String stockage,String ecran)
    {
        super(id,codeBarre,dateFinGarantie);
        this.cpu=cpu;
        this.ram=ram;
        this.stockage=stockage;
        this.ecran=ecran;
    }
}
