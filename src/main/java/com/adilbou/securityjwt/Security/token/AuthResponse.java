package com.adilbou.securityjwt.Security.token;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AuthResponse {
    String accessToken;
    String refreshToken;
}
