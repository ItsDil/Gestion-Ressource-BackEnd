package com.adilbou.securityjwt.DTOs;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;



@Data
public class PanneDTO_Dep {
    List<PannesDTO> pannes=new ArrayList<>();
    private  String departementName;

}
