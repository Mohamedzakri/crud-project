package it.mohamed.crudproject.config.security;

import it.mohamed.crudproject.config.token.TokenRepo;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component// makes the class a managed bean, but what's a managed bean?
public class JwtAuthenticationFilter extends OncePerRequestFilter /* The JwtAuthenticationFilter is a class that checks for the authentication,
 of a user when he makes a request. the OncePerRequestFilter class ensures that the class JwtAuthenticationFilter will be called for every request by a user
  the OncePerRequestFilter extends the class GenericFilterBean which by     its self implements the Filter interface,
  so basically we either can implement Filter or extend the OncePerRequestFilter*/{

    private final JwtService jwtService;
    // UserDetailsService it's an interface included in spring security, and it has different implementations, but we will create our own to fetch the username
    private final UserDetailsService userDetailsService;
    private final TokenRepo tokenRepository;

    @Autowired
    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService, TokenRepo tokenRepository ){
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.tokenRepository = tokenRepository;
    }
    /*
    The doFilterInternal method is part of the OncePerRequestFilter and when we extend it we should implement
    The request is the user's request
    The response is the user's response
    The filterChain is chain of responsibility design pattern
    @NotNull because they can't be null
     */
    /*
    The cycle that the request goes through before elaborating it goes as follows:
    First checks the validity of the token (1)
    then it calls the Jwt service to extract the user info (2) what user info do we need to check for?
    afterwords it calls the userDetailsService to check if the user exist
    then when all the validations are complete we update the securityContextHolder (3)
    ....
     */
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        if (request.getServletPath().contains("/api/v1/auth")) {
            filterChain.doFilter(request, response);
            return;
        }
        final String authHeader = request.getHeader("Authorization");// extracts the Authorization header from the request that contains the jwt token
        final String jwt;
        final String userEmail;
        // checks if the token is null and doesn't start with the Bearer
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);// what does it do?
            return;// it will refuse the request but what would the user receive?
        }
        jwt = authHeader.substring(7);// what's a substring?
        userEmail = jwtService.extractUsername(jwt);
        /*
        check the email that is extracted from the jwt token if it's valid and if the user is already authenticated,
        so to not recall the jwt service and re-authenticated an already authenticated user
         */
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            var isTokenValid = tokenRepository.findByToken(jwt)
                    .map(t -> !t.isExpired() && !t.isRevoked())
                    .orElse(false);
            /*
            we need to check the validity of the token by calling the method isTokenValid,
            and then we create the UsernamePasswordAuthenticationToken with a constructor
            by passing the variables:
                userDetails,// the username we called from the userDetailsService.
                null, //because we have no credentials.
                userDetails.getAuthorities()
             inside this if we update the SecurityContextHolder.
             */
            if (jwtService.isTokenValid(jwt, userDetails) && Boolean.TRUE.equals(isTokenValid)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                /*
                we update the authToken with the details from the request
                 */
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                /*
                we update the SecurityContextHolder with the authToken variable
                 */
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        /*
        we call the filterChain to pass to the next filter to be executed.
         */
        filterChain.doFilter(request, response);
    }
}
