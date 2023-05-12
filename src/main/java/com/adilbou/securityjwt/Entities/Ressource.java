package com.adilbou.securityjwt.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;


@Entity
@AllArgsConstructor @NoArgsConstructor @Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="ressource_type", discriminatorType = DiscriminatorType.STRING)
@OnDelete(action = OnDeleteAction.CASCADE)
public class Ressource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codeBarre;
    private String dateLivraison;
    private String dateFinGarantie;
    private String dateDemande;
    private String marque;
    private double prix;
    private String type;
    private Integer idFournisseur;
    private Integer idMembreDepartement;
    private String DeparementName;
    private Boolean isDeleted = false;


}
