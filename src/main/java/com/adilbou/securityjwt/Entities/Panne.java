package com.adilbou.securityjwt.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Panne
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dateApparition;
    @Column(columnDefinition = "TEXT")
    private String constat;
    private String dateConstat;
    private Boolean isTreated;
    private Integer idMembreDepartement;
    private Integer idTechnicien;
    private Long idRessource;
    private Boolean etat;
}