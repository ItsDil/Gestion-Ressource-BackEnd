package com.adilbou.securityjwt.Entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Builder @AllArgsConstructor @NoArgsConstructor @Data
@DiscriminatorValue("Imprimante")
@OnDelete(action = OnDeleteAction.CASCADE)
public class Imprimante extends Ressource {

    private String resolution;
    private int vitesseImpression;



}
