package it.mohamed.crudproject.config.security;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtAuthFilter {
    private final UserAuthenticationProvider userAuthenticationProvider;


}
