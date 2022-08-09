package com.bitcamp.AuthTodo.controller;

import com.bitcamp.AuthTodo.dto.ResponseDTO;
import com.bitcamp.AuthTodo.dto.UserDTO;
import com.bitcamp.AuthTodo.model.UserEntity;
import com.bitcamp.AuthTodo.security.TokenProvider;
import com.bitcamp.AuthTodo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private TokenProvider tokenProvider;

//    @Bean
//    public PasswordEncoder PasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    public PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @PostMapping(value = "/signup")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
        try{
            // 수신 데이터 유효성 검사
            if(userDTO == null || userDTO.getUsername() == null || userDTO.getPassword() == null) {
                if(userDTO == null) {
                    throw new RuntimeException("UserController registerUser userDTO");
                }else if(userDTO.getUsername() == null) {
                    throw new RuntimeException("UserController registerUser userDTO.username");
                }else if(userDTO.getPassword() == null) {
                    throw new RuntimeException("UserController registerUser userDTO.password");
                }
            }
            UserEntity user = UserEntity.builder()
                    .username(userDTO.getUsername())
//                    .password(userDTO.getPassword())
                    .password(passwordEncoder.encode(userDTO.getPassword()))
                    .build();



            // 서비스를 이용해 리포지토리에 유저 저장
            UserEntity registeredUser = userService.signUp(user);

            // response 객체 생성 (password 제외)
            UserDTO resDTO = UserDTO.builder()
                    .id(registeredUser.getId())
                    .username(registeredUser.getUsername())
//                    .token()
                    .build();

            return ResponseEntity.ok().body(resDTO);

        }catch (Exception e) {
            ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    @PostMapping(value = "/signin")
    public ResponseEntity<?> auth(@RequestBody UserDTO userDTO) {
        UserEntity user = userService.auth(userDTO.getUsername(), userDTO.getPassword(), passwordEncoder);
        System.out.println(user.getPassword());

        if(user != null) {
            final String token = tokenProvider.create(user);

            final UserDTO responseUserDTO = UserDTO.builder()
                    .username(user.getUsername())
                    .id(user.getId())
                    .token(token)
                    .build();

            return ResponseEntity.ok().body(responseUserDTO);
        }else {
            ResponseDTO responseDTO = ResponseDTO.builder().error("login failed").build();
            return ResponseEntity.badRequest().body(responseDTO);
        }

    }

}




























