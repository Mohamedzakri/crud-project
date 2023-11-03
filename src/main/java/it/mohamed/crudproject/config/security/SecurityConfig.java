package it.mohamed.crudproject.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig{
    private final UserAuthenticationEntryPoint userAuthenticationEntryPoint;
    private final UserAuthenticationProvider userAuthenticationProvider;

    @Bean
    //This method is responsible for configuring the security settings for the application.
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        //HttpSecurity class is a fundamental part of the configuration process for securing your web application.
        //It allows you to define how to handle HTTP requests in terms of authentication, authorization, etc.
        //httpSecurity is used to configure the security settings.
        httpSecurity
                //exceptionHandling Configures the exception handling. It sets the authentication entry point to userAuthenticationEntryPoint.
                .exceptionHandling(customizer -> customizer.authenticationEntryPoint(userAuthenticationEntryPoint))
                //Adds a custom JwtAuthFilter before the BasicAuthenticationFilter. This is a filter that presumably handles JWT-based authentication.
                .addFilterBefore(new JwtAuthFilter(userAuthenticationProvider), BasicAuthenticationFilter.class)
                //Disables CSRF protection. This is often done for stateless API endpoints.
                .csrf(AbstractHttpConfigurer::disable)
                //Configures the session management.
                // It sets the session creation policy to STATELESS,
                // indicating that the application doesn't use sessions to maintain user state.
                .sessionManagement(customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //Configures request authorization.
                // It allows unauthenticated access (permitAll) to POST requests for "/login" and "/register" paths.
                // For any other request, it requires authentication (authenticated).
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(HttpMethod.POST, "/login", "/register").permitAll()
                        .anyRequest().authenticated())
        ;
        return httpSecurity.build();
    }
}
