package com.bitcamp.AuthTodo.service;

import com.bitcamp.AuthTodo.model.UserEntity;
import com.bitcamp.AuthTodo.persistence.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // username 유효성 및 중복검사
    public UserEntity signUp(final UserEntity userEntity){
        // 1. userEntity 유효성 검사.
        if(userEntity == null || userEntity.getUsername() == null) {
            throw new RuntimeException("signup Error");
        }
        final String username = userEntity.getUsername();

        // 2. 중복 검사
//        validate(username);
        if(userRepository.existsByUsername(username)) {
            log.warn("username already exist {}", username);
            throw new RuntimeException("username already exists");
        }

        return userRepository.save(userEntity);

    }

    public Boolean validate(String username) {
        if(userRepository.existsByUsername(username)) {
            log.warn("username already exist {}", username);
            throw new RuntimeException("username already exists");
        }
        return null;
    }

    // username & password 일치 검사
    public UserEntity auth(final String username, final String password, final PasswordEncoder encoder){
        final UserEntity originalUser = userRepository.findByUsername(username);

        if(originalUser != null && encoder.matches(password, originalUser.getPassword())) {
            return originalUser;
        }

        return null;
    }

}
