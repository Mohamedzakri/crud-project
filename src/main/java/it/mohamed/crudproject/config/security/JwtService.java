package it.mohamed.crudproject.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    @Value("${application.security.jwt.secret-key}")
    private String secretKey;
    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;
    @Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshExpiration;

    /*
    A method that calls extractClaim and returns the user subject that can be an email, etc. from a token //TO-DO: needs more specifying
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /*
    A method that calls the extractAllClaims and returns a single claim from a token //TO-DO: needs more specifying
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    /*
    A method that generates a token solely on the userDetails and calls the generateToken method with a new hashMap and userDetails
     */
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }


    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        return buildToken(extraClaims, userDetails, jwtExpiration);
    }

    public String generateRefreshToken(
            UserDetails userDetails
    ) {
        return buildToken(new HashMap<>(), userDetails, refreshExpiration);
    }

    /*
    A method that generates a token and takes for input a map of extraClaims and returns a token //TO-DO: needs more specifying
     */
    private String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            long expiration
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))// sets the time when the token was generated
                .setExpiration(new Date(System.currentTimeMillis() + expiration))// sets the expiration for the token
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)// sets the signing algo
                .compact();// will generate and return the token
    }

    /*
    A method that validates the token based on if the generated token belongs to the user,
    it checks the username extracted from the method extractUsername, and it compares it to the user's username with userDetails,
    and it also checks if the token isn't expired by calling the method isTokenExpired
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }
    /*
    it calls the extractExpiration, and it returns a boolean depending on if the expiration date of the token is before today
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /*
    it calls the extractClaim method, and it calls for the getExpiration of the token
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                /*
                signing key is a secret used to sign the jwt. and it's used to create the signature part of jwt.
                and it used to verify if the identity of the user is the same as the one who made the request
                and to make sure the message wasn't changed along the way
                the signing key is used with the signing algo that is contained in the jwt header to create the signature */
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);//it decodes the secret with BASE64 decoder. what's byte
        return Keys.hmacShaKeyFor(keyBytes);// what does this method do?
    }
}