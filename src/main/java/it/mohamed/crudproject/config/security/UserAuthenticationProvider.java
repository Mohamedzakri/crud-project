package it.mohamed.crudproject.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import it.mohamed.crudproject.dto.UserDto;
import it.mohamed.crudproject.service.authentication.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Collections;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class UserAuthenticationProvider {
    private final UserService userService;
    /*
    This annotation is used to inject a property value from the application configuration.
    It retrieves the value of the property "security.jwt.token.secret-key" and,if not found, defaults to "secret-key".
    This secret key is used for signing and verifying JWTs.
        where's value from the application configuration?
        what's the default secret key
     */
    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;

    /*
    This method is annotated with @PostConstruct, which means it is executed after the bean is initialized.
    In this method, the secret key is encoded as a Base64 string.
    This encoding is done to prepare the secret key for use with JWT algorithms.
            what does bean is initialized mean?
            what does secret key is encoded as a Base64 string mean?
     */
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    /*
   This method is used to create a JWT token.
   JWT Jason Web Token is a self-contained Json Object.
   It consists of 3 parts: a header, a signature, and a payload.
   The Header defines the type of token and the algo used.
   The payload contains the claims in this case the user-name. == "UserDto object as an argument"
   The signature is used to verify the authenticity of the user.

   It generates a JWT using the secretKey.
   It includes information such as the .withSubject (usually the user's login),
   the issued date,
   the expiration date,
   and a custom claim for the user's name.
   The JWT is signed with the HMAC256 algorithm using the secretKey,
   and the signed token is returned.
    */
    public String createToken(UserDto user) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + 3600000); //1 hour

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.create()
                .withSubject(user.getLogin())
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .withClaim("userName", user.getUserName())
                .sign(algorithm);
    }

    /*
    This method is used to validate a JWT token.
    It takes a token as input and verifies its:
    signature and claims using (2).
    If the token is valid, it decodes it to obtain the subject and custom claim for the user's name(4).
    It then creates and returns an instance of UsernamePasswordAuthenticationToken
    containing a UserDto object and an empty list of authorities, which can be used for user authentication.
     */
    public Authentication validateToken(String token) {
        // (1) create the algo
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        // (2) create the verifier
        JWTVerifier verifier = JWT.require(algorithm)
                .build();

        // (3) creates the decoded object and uses the verify method to verify the token
        DecodedJWT decoded = verifier.verify(token);

        // (4) in case the token is valid it decodes it to obtain the subject and the custom clain
        UserDto userDto = UserDto.builder()
                .login(decoded.getSubject())
                .userName(decoded.getClaim("userName").asString())
                .build();

        return new UsernamePasswordAuthenticationToken(userDto, null, Collections.emptyList());
    }
    /*
    This method is used to validate a JWT token.
    it creates an algo, verifier, and the decoded.
    and then it creates a userDto by fetching a user by its login
    It then creates and returns an instance of UsernamePasswordAuthenticationToken
    containing a UserDto object and an empty list of authorities, which can be used for user authentication.
     */
    public Authentication validateTokenStrongly(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        JWTVerifier verifier = JWT.require(algorithm)
                .build();
        DecodedJWT decodedJWT = verifier.verify(token);
        UserDto userDto = userService.findByLogin(decodedJWT.getSubject());
        return new UsernamePasswordAuthenticationToken(userDto, null, Collections.emptyList());
    }

}
