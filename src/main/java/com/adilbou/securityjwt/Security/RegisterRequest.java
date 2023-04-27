package com.adilbou.securityjwt.Security;


import com.adilbou.securityjwt.Entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class RegisterRequest {

    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private List<Role> roles;
    private String society;
    private String entreprise;
    private String departement;
    private String speciality;




}
