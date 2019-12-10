package org.daniels.spring.todo.rest.v1;

import org.daniels.spring.todo.domain.Todo;
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
    public List<Todo> retrieveAll() {
        log.info("REST request to retrieve all Todos");

        final List<Todo> list = todoService.retrieveAll();
        return list;
    }

    @GetMapping("/todos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Todo retrieveTodo(@PathVariable Long id) {
        log.info("REST request to retrieve Todo by id: {} ", id);

        final Todo todo = todoService.findById(id);

        return todo;
    }

    @PostMapping("/todos")
    @ResponseStatus(HttpStatus.CREATED)
    public Todo createTodo(@RequestBody final Todo todo) {
        log.debug("REST request to save Todo : {}", todo);
        if (todo.getId() != null) {
            throw new RuntimeException("A new todo cannot have an ID");
        }

        Todo createTodo = todoService.add(todo);

        return  createTodo;
    }

    @PutMapping("/todos")
    @ResponseStatus(HttpStatus.OK)
    public Todo updateTodo(@RequestBody final Todo todo) {
        log.debug("REST request to update Todo : {}", todo);
        if (todo.getId() == null) {
            throw new RuntimeException("Invalid id");
        }

        Todo createTodo = todoService.update(todo);

        return  createTodo;
    }

}
