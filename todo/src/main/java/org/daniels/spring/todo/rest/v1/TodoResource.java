package org.daniels.spring.todo.rest.v1;

import org.daniels.spring.todo.model.TodoDTO;
import org.daniels.spring.todo.service.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(TodoResource.BASE_URL)
public class TodoResource {

    private final Logger log = LoggerFactory.getLogger(TodoResource.class);
    private final TodoService todoService;

    public TodoResource(TodoService todoService) {
        this.todoService = todoService;
    }

    public static final String BASE_URL = "/api/v1";

    @GetMapping("/todos")
    @ResponseStatus(HttpStatus.OK)
    public List<TodoDTO> retrieveAll() {
        log.info("REST request to retrieve all Todos");

        final List<TodoDTO> list = todoService.retrieveAll();
        return list;
    }

    @GetMapping("/todos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TodoDTO retrieveTodo(@PathVariable Long id) {
        log.info("REST request to retrieve Todo by id: {} ", id);

        final TodoDTO todoDTO = todoService.findById(id);

        return todoDTO;
    }

    @PostMapping("/todos")
    @ResponseStatus(HttpStatus.CREATED)
    public TodoDTO createTodo(@RequestBody final TodoDTO todoDTO) {
        log.debug("REST request to save Todo : {}", todoDTO);
        if (todoDTO.getId() != null) {
            throw new RuntimeException("A new todo cannot have an ID");
        }

        TodoDTO createdTodo = todoService.add(todoDTO);

        return  createdTodo;
    }

    @PutMapping("/todos")
    @ResponseStatus(HttpStatus.OK)
    public TodoDTO updateTodo(@RequestBody final TodoDTO todoDTO) {
        log.debug("REST request to update Todo : {}", todoDTO);
        if (todoDTO.getId() == null) {
            throw new RuntimeException("Invalid id");
        }

        TodoDTO updatedTodoDTO = todoService.update(todoDTO);

        return updatedTodoDTO;
    }

    @DeleteMapping("/todos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTodo(@PathVariable Long id) {
        log.info("REST request to delete Todo by id: {} ", id);

        todoService.delete(id);
    }

}
