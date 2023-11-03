package it.mohamed.crudproject.service.authentication;

import it.mohamed.crudproject.dto.UserDto;
import it.mohamed.crudproject.exception.AppException;
import it.mohamed.crudproject.model.UserEntity;
import it.mohamed.crudproject.repo.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper){
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }
    public UserDto findByLogin(String login) {
        UserEntity user = userRepository.findByLogin(login)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        return  modelMapper.map(user, UserDto.class);
    }
}
