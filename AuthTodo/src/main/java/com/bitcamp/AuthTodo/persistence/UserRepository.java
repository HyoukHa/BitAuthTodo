package com.bitcamp.AuthTodo.persistence;

import com.bitcamp.AuthTodo.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> { // generic 으로 <엔티티, pk 의 타입> 을 넣어준다.

    UserEntity findByUsername(String username);

    Boolean existsByUsername(String username);

    UserEntity findByUsernameAndPassword(String username, String password);
}
