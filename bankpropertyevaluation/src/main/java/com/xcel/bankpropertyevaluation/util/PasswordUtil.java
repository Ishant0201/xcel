package com.xcel.bankpropertyevaluation.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtil {
    /**
     * Check if the provided plain text password matches the hashed password
     *
     * @param plainTextPassword
     * @param hashedPassword
     * @return
     */
    public boolean checkPassword(String plainTextPassword, String hashedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(plainTextPassword, hashedPassword);
    }

}
