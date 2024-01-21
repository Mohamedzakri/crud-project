package it.mohamed.crudproject.config.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.mohamed.crudproject.config.security.JwtService;
import it.mohamed.crudproject.config.token.Token;
import it.mohamed.crudproject.config.token.TokenRepo;
import it.mohamed.crudproject.config.token.TokenType;
import it.mohamed.crudproject.enums.LogMessage;
import it.mohamed.crudproject.model.UserEntity;
import it.mohamed.crudproject.repo.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.NoSuchElementException;

/*
Service class containing methods for the controller
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final TokenRepo tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request){
        log.info(LogMessage.INVOKE_SERVICE_REGISTRY_METHOD.toString());
        try {
            if(request == null){
                log.warn(LogMessage.REGISTER_REQUEST_BODY_IS_NULL.toString());
                return null;
            }
            log.info(LogMessage.CREATING_USER.toString());
            var user = UserEntity.builder()
                    .userName(request.getUserName())
                    .firstName(request.getFirstname())
                    .lastName(request.getLastname())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword())) // we need to encode the password before saving it to the db
                    .role(request.getRole())
                    .build();
            log.info(LogMessage.NEW_USER_CREATED.toString());

            log.info(LogMessage.SAVING_NEW_USER.toString());
            var savedUser = repository.save(user);
            log.info(LogMessage.NEW_USER_SAVED.toString());

            log.info(LogMessage.SAVING_JWT_TOKEN.toString());
            var jwtToken = jwtService.generateToken(user);
            log.info(LogMessage.NEW_JWT_TOKEN_SAVED.toString());

            log.info(LogMessage.SAVING_REFRESH_TOKEN.toString());
            var refreshToken = jwtService.generateRefreshToken(user);
            log.info(LogMessage.NEW_REFRESH_TOKEN_SAVED.toString());

            log.info(LogMessage.SAVING_USER_TOKEN.toString());
            saveUserToken(savedUser, jwtToken);
            log.info(LogMessage.NEW_USER_TOKEN_SAVED.toString());

            log.info(LogMessage.EXIT_REGISTER_SERVICE_METHOD.toString());

            return AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .refreshToken(refreshToken)
                    .build();

        }catch (Exception ex){
           log.error("exception" + ex);
           return null;
        }
    }

    /*
     this method is used to authenticate users
     */
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        log.info(LogMessage.INVOKE_SERVICE_AUTHENTICATE_METHOD.toString());
        authenticationManager.authenticate(/*
         we call the authenticate method, and we pass it a new object of type UsernamePasswordAuthenticationToken.
         this method will do authenticate all the authentication for the user and if the user email or password are not correct,
         it will throw an exception.
         */
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );// past this point, the user is authenticated and next we have to generate a token and send it back
        log.info(LogMessage.SELECTING_USER_BY_EMAIL.toString());
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new NoSuchElementException("User not found for email: " + request.getEmail()));
        log.info(LogMessage.SELECTED_USER_BY_EMAIL.toString());
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        log.info(LogMessage.REVOKING_ALL_USER_TOKENS.toString());
        revokeAllUserTokens(user);
        log.info(LogMessage.ALL_USER_TOKENS_REVOKED.toString());
        log.info(LogMessage.SAVING_USER_TOKEN.toString());
        saveUserToken(user, jwtToken);
        log.info(LogMessage.NEW_USER_TOKEN_SAVED.toString());
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    private void saveUserToken(UserEntity user, String jwtToken) {
        var token = Token.builder()
                .userEntity(user)
                .tokenCode(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(UserEntity user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.repository.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }
}