package it.mohamed.crudproject.config.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder // builds an object with the builder design pattern
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
    String password;
    private String email;
}
