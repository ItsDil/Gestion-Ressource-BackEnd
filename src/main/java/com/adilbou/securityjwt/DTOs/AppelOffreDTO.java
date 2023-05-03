package com.adilbou.securityjwt.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppelOffreDTO {

    List<BesoinAppOffDTO> besoinAppOffDTOS = new ArrayList<>();
}
