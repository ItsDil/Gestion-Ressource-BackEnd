package com.adilbou.securityjwt.DTOs;

import java.util.Random;

public class CodeBarre {

    private static final String CHARACTERS = "0123456789";
    private static final Random random = new Random();

    public static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }
}