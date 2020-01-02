package org.daniels.spring.todo.rest.v1;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.daniels.spring.todo.model.TodoDTO;
import org.daniels.spring.todo.rest.v1.errors.TitleNotValidException;
import org.daniels.spring.todo.service.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
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

        final List<TodoDTO> list = todoService.retriveAllOrderedByPriority();
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
    public ResponseEntity<TodoDTO> createTodo(@Valid @RequestBody final TodoDTO todoDTO) throws URISyntaxException {
        log.info("REST request to save Todo : {}", todoDTO);
        if (todoDTO.getId() != null) {
            throw new RuntimeException("A new todo cannot have an ID");
        }
       if (!checkTitleLength(todoDTO.getTitle())) {
           // already done by @Valid
           // throw new TitleNotValidException("Title should have at least 3 characters");
        }

        TodoDTO createdTodo = todoService.add(todoDTO);

        return ResponseEntity.created(new URI(BASE_URL + createdTodo.getId())).body(createdTodo);
    }

    @PutMapping("/todos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TodoDTO updateTodo(@PathVariable Long id, @RequestBody final TodoDTO todoDTO) {
        log.info("REST request to update Todo : {}", todoDTO);
        if (todoDTO.getId() == null) {
            throw new RuntimeException("Invalid id");
        }

        TodoDTO updatedTodoDTO = todoService.update(todoDTO);

        return updatedTodoDTO;
    }

    @PutMapping("/todos")
    @ResponseStatus(HttpStatus.OK)
    public List<TodoDTO> updateTodo2(@RequestBody final List<TodoDTO> todos) {
        log.info("REST request to update2 Todo : {}", todos);

        List<TodoDTO> updList = Lists.newLinkedList();
        for (final TodoDTO todoDTO : todos) {
            updList.add(todoService.update(todoDTO));
        }
        return updList;
    }

    @DeleteMapping("/todos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTodo(@PathVariable Long id) {
        log.info("REST request to delete Todo by id: {} ", id);

        todoService.delete(id);
    }

    private static boolean checkTitleLength(String title) {
        return !Strings.isNullOrEmpty(title)
                && title.length() >= TodoDTO.TITLE_MIN_LENGTH
                && title.length() <= TodoDTO.TITLE_MAX_LENGTH;
    }
}
