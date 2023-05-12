package com.adilbou.securityjwt.DTOs;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ImprOrd {
    private String resolution;
    private int vitesse;
    private String cpu;
    private int ram;
    private String stockage;
    private String ecran;
}
