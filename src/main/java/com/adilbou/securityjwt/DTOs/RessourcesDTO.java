package com.adilbou.securityjwt.DTOs;

import com.adilbou.securityjwt.Entities.Ressource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RessourcesDTO
{
    private Long id;
    private String codeBarre;
    private String dateFinGarantie;
    private String dateDemande;
    private String typeRessource;
    private Boolean etat;
    private Boolean isTraited;
    private ImprOrd imprOrd;

    public RessourcesDTO(Long id, String codeBarre, String dateFinGarantie) {
        this.codeBarre = codeBarre;
        this.dateFinGarantie = dateFinGarantie;
        this.id = id;
    }
}
