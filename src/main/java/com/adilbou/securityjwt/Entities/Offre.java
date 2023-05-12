package com.adilbou.securityjwt.Entities;

import com.adilbou.securityjwt.Enumration.ETAT_OFFRE;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Offre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String date;

    private Boolean isAffected;
    private  Double prix;
    private Integer idFournisseur;

    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private AppelOffre appelOffre;

    @Enumerated(EnumType.STRING)
    private ETAT_OFFRE etat;

}