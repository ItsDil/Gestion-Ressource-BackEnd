package com.adilbou.securityjwt.Entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
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
@DiscriminatorValue(  "TECH" )

public class Technicien extends User{

    private String speciality;


}
