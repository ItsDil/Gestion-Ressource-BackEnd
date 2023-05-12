package com.adilbou.securityjwt.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppleOffreDTOResp {

    private List<OffreDTOResp> offreDTOResps = new ArrayList<>();
}
