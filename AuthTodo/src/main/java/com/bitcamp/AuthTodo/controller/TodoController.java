package com.bitcamp.AuthTodo.controller;

import com.bitcamp.AuthTodo.dto.ResponseDTO;
import com.bitcamp.AuthTodo.dto.TodoDTO;
import com.bitcamp.AuthTodo.model.TodoEntity;
import com.bitcamp.AuthTodo.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(value = "/api/todo")
public class TodoController {
    @Autowired
    private TodoService todoService;

    /**
     * 생성 (Create Todo) 구현
     */
    @PostMapping("")
    public ResponseEntity<?> CreateTodo(@AuthenticationPrincipal String userId, @RequestBody TodoDTO dto){
        try {
//            String temporaryUserId = "temporary_user";

            // 1. TodoEntity 로 변환
            TodoEntity entity = TodoDTO.toEntity(dto);

            // 2. id를 null 로 초기화 한다. 생성 당시에는 id가 없어야 하기 때문이다.
            entity.setId(null);

            // 3. 임시 유저 아이디를 설정해 준다.
            entity.setUserId(userId);

            // 4. 서비스를 이용해서 Entity 를 생성한다.
            List<TodoEntity> entities = todoService.create(entity);

            // 5. java stream 을 이용해 리턴된 entity list 를 TodoDTO 리스트로 변환
            List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

            //6. 변환된 TodoDTO 리스트를 이용해 ResponseDTO 를 초기화
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().resList(dtos).build();

            // 7. ResponseDTO 를 리턴
            return ResponseEntity.ok().body(response);


        }catch (Exception e){
            String error = e.getMessage();
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * 조회
     */

    @GetMapping("")
    public ResponseEntity<?> retrieveTodoList(@AuthenticationPrincipal String userId) {
//        String temporaryUserId = "temporary_user";

        List<TodoEntity> entities = todoService.retrieve(userId);

        List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
        ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().resList(dtos).build();
        return ResponseEntity.ok().body(response);
    }

    /**
     * 수정
     */
    @PutMapping(value = "")
    public ResponseEntity<?> updateTodo(@AuthenticationPrincipal String userId, @RequestBody TodoDTO todoDTO) {
//        String temporaryUserId = "temporary_user";

        TodoEntity entity = TodoDTO.toEntity(todoDTO);

        entity.setUserId(userId);

        List<TodoEntity> entities = todoService.update(entity);

        List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
        ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().resList(dtos).build();

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping(value = "")
    public ResponseEntity<?> deleteTodo(@AuthenticationPrincipal String userId, @RequestBody TodoDTO todoDTO) {
        try {
//            String temporaryUserId = "temporary_user";

            TodoEntity entity = TodoDTO.toEntity(todoDTO);

            entity.setUserId(userId);

            List<TodoEntity> entities = todoService.delete(entity);

            List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().resList(dtos).build();

            return ResponseEntity.ok().body(response);
        }catch (Exception e) {
            String error = e.getMessage();
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }
}
