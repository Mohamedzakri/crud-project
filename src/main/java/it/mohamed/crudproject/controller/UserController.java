package it.mohamed.crudproject.controller;


import it.mohamed.crudproject.model.UserEntity;
import it.mohamed.crudproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }
    @GetMapping(value = "/{param}/details")
    public ResponseEntity<UserEntity> getUserByParam(@PathVariable("param") Object param) {
        return ResponseEntity.ok(userService.getUser(param));
    }

}
