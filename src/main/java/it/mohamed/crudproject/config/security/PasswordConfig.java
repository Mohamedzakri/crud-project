package it.mohamed.crudproject.config.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordConfig {
    /* A PasswordEncoder is an interface for hashing and verifying passwords.
            It's a useful method when want to store plain text passwords in a db without exposing its true value */
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(); // BCryptPasswordEncoder is a class
                                            // that uses the bcrypt algorithm to hash the password and making them difficult to reverse engineer
    }
}
