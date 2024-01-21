package it.mohamed.crudproject.service;

import it.mohamed.crudproject.model.UserEntity;
import it.mohamed.crudproject.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity getUser(Object param) {
        System.out.println("service");
        return userRepository.getUserByParam(param);
    }

    public Optional<UserEntity> getUserEmail(String email) {
        System.out.println("Optional<UserEntity> getUserEmail(String email)");
        return userRepository.findByEmail(email);
    }
}
