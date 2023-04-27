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
@DiscriminatorValue("Ordinateur")
@OnDelete(action = OnDeleteAction.CASCADE)
public class Ordinateur extends Ressource {

    private String cpu;
    private int ram;
    private String disqueDur;
    private String ecran;

}
