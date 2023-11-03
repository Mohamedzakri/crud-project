package it.mohamed.crudproject.config.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter /*OncePerRequestFilter extends the javax.servlet.Filter
                                                          and uses the "doFilter()" method is executed once per http request*/ {
    private final UserAuthenticationProvider userAuthenticationProvider;

    @Override
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            FilterChain filterChain) throws ServletException, IOException {
        // create a header of type string that contains the authorization from the header
        String header = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);

        if (header != null) {
            // if the header coming from http request isn't null then it get saved into an array
            String[] authElements = header.split(" ");
            if (authElements.length == 2 && "Bearer".equals(authElements[0])) {
                try {
                    //if it's a get request
                    if ("GET".equals(httpServletRequest.getMethod())){
                        /*
                         it calls the validateToken from the userAuthenticationProvider class,
                         and it passes the second element in the authElements array
                         */
                        SecurityContextHolder.getContext().setAuthentication(
                                userAuthenticationProvider.validateToken(authElements[1])
                        );
                    }
                    else {
                        SecurityContextHolder.getContext().setAuthentication(
                                userAuthenticationProvider.validateTokenStrongly(authElements[1]));
                    }
                }catch (RuntimeException e){
                    SecurityContextHolder.clearContext();
                    throw e;
                }
            }
        }
        //the filterChain is invoked with the updated request and response objects,
        // allowing the request to continue processing by other filters or reach the application's endpoints if authentication is successful.
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
