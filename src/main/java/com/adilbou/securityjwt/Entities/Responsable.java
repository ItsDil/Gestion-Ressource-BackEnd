package com.adilbou.securityjwt.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Inheritance( strategy = InheritanceType.SINGLE_TABLE )
@DiscriminatorValue(  "RESP" )
public class Responsable extends User{

    private String Entreprise;

}
