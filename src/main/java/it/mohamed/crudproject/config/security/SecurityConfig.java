package it.mohamed.crudproject.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static it.mohamed.crudproject.enums.Permission.*;
import static it.mohamed.crudproject.enums.Role.ADMIN;
import static it.mohamed.crudproject.enums.Role.MANAGER;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

/*
in the JwtAuthenticationFilter and JwtService are used to call the JwtAuthFilter to check the jwt token,
and to validate the jwt, and to update the security context holder.
but we need a configuration class to tell spring to use the security filter we have just configured.
so that why we have created the SecurityConfig.
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {
    private static final String URL = "/api/v1/management/**";
    private static final String[] WHITE_LIST_URL = {"/api/v1/auth/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html"};
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    /*
    when the app starts the SecurityConfig will look for a bean of type SecurityFilterChain,
    and this bean is capable of configuring all the http security requests
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)// IDK why we disable it
                .authorizeHttpRequests(req ->
                        /*
                        at this point we set the requestMatchers with a white list
                         */
                        req.requestMatchers(WHITE_LIST_URL)// it will allow all the URL in this array
                                .permitAll()
                                .requestMatchers(URL).hasAnyRole(ADMIN.name(), MANAGER.name())
                                .requestMatchers(GET, URL).hasAnyAuthority(ADMIN_READ.name(), MANAGER_READ.name())
                                .requestMatchers(POST, URL).hasAnyAuthority(ADMIN_CREATE.name(), MANAGER_CREATE.name())
                                .requestMatchers(PUT, URL).hasAnyAuthority(ADMIN_UPDATE.name(), MANAGER_UPDATE.name())
                                .requestMatchers(DELETE, URL).hasAnyAuthority(ADMIN_DELETE.name(), MANAGER_DELETE.name())
                                .anyRequest()
                                /*
                                and the rest of the request should be authenticated
                                 */
                                .authenticated()
                )
                /*
                when we have set the filter, we have configured it to be by OnePerRequest and every request should be authenticated,
                so the authentication shouldn't be stored, and it should be stateless so this way each request should be authenticated.
                 */
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                /*
                we use the addFilterBefore, so we can call use the jwtAuthFilter that we have created and then use the UsernamePasswordAuthenticationFilter
                basically when we validate the jwt and update the security context holder and then use the UsernamePasswordAuthenticationFilter.
                 */
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout ->
                        logout.logoutUrl("/api/v1/auth/logout")
                                .addLogoutHandler(logoutHandler)
                                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
                )
        ;

        return http.build();
    }
}
