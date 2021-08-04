package com.klarios.sistemaweb.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncripcionPassword {
    public static String encriptar(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}
