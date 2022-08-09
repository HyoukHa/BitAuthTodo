package com.bitcamp.AuthTodo.persistence;

import com.bitcamp.AuthTodo.model.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, String> {

//    @Query("SELECT t FROM TodoEntity t WHERE t.userId = ?1")
    List<TodoEntity> findByUserId(String userId);
}