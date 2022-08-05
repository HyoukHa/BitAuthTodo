package com.bitcamp.AuthTodo.service;

import com.bitcamp.AuthTodo.model.TodoEntity;
import com.bitcamp.AuthTodo.persistence.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TodoService {

    @Autowired
    private TodoRepository repository;

    public List<TodoEntity> create(final TodoEntity entity) {
        // 1. 저장할 entity가 유효한지 확인

        validate(entity);

        repository.save(entity);


        log.info("Entity id : {} is saved", entity.getId());

        return repository.findByUserId(entity.getUserId());
    }


    public List<TodoEntity> retrieve(final String userId) {
        return repository.findByUserId(userId);
    }


    public List<TodoEntity> update(final TodoEntity entity) {
        validate(entity);
        final Optional<TodoEntity> original = repository.findById(entity.getId());

        original.ifPresent(todo -> {
            // 반환된 Todo Entity 가 존재 하면 값을 새 entity 의 값으로 덮어씌운다.
            todo.setTitle(entity.getTitle());
            todo.setDone(entity.isDone());

            // 데이터베이스에 새 값을 저장한다.
            repository.save(todo);
        });
        return retrieve(entity.getUserId());
    }

    public List<TodoEntity> delete(final TodoEntity entity) {
        validate(entity);
        try{
            repository.delete(entity);
        }catch (Exception e){
            log.error("error deleting entity", entity.getId(), e);
            throw new RuntimeException("error deleting entity" + entity.getId());
        }
        return retrieve(entity.getUserId());
    }

    private void validate(final TodoEntity entity) {
        // 유효성 검증
        if(entity == null) {
            throw new RuntimeException("Entity can not be null");
        }
        if(entity.getUserId() == null) {
            throw new RuntimeException("unknown User");
        }
    }


}
