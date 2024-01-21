package it.mohamed.crudproject.controller;


import it.mohamed.crudproject.enums.LogMessage;
import it.mohamed.crudproject.model.UserEntity;
import it.mohamed.crudproject.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping(value = "/hi")
    public void hi(Authentication authentication){
        log.info("hi controller method");
    }
    @GetMapping(value = "/{param}")
    public ResponseEntity<UserEntity> getUserByParam(@PathVariable("param") Object param) {
        log.info(LogMessage.INVOKE_CONTROLLER_SELECT_USER_BY_PARAM_METHOD.toString());
        return ResponseEntity.ok(userService.getUser(param));
    }

    @GetMapping(value = "/email/{email}")
    public ResponseEntity<Optional<UserEntity>> getUserByEmail(@PathVariable("email") String email) {
        log.info(LogMessage.INVOKE_CONTROLLER_SELECT_USER_BY_EMAIL_METHOD.toString());
        return ResponseEntity.ok(userService.getUserEmail(email));
    }
}
