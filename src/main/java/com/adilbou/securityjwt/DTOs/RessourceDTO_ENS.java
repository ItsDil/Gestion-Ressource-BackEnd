package com.adilbou.securityjwt.DTOs;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RessourceDTO_ENS
{

    List<RessourcesDTO> ressourcesDTOList=new ArrayList<>();

}
