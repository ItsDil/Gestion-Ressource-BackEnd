package com.adilbou.securityjwt.DTOs;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class PanneDTO_Tech {
    List<PanneDTO_Dep> panneDTODeps = new ArrayList<>();
}
