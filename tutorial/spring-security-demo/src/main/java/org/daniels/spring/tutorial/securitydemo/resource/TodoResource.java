package org.daniels.spring.tutorial.securitydemo.resource;

import com.google.common.collect.Lists;
import org.daniels.spring.tutorial.securitydemo.model.Todo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(TodoResource.BASE_URL)
public class TodoResource {

    public static final String BASE_URL = "/api";

    @GetMapping("/todo/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Todo getTodo(@PathVariable Long id) {
        final Todo fakeTodo = new Todo();
        return fakeTodo.id(id).title("First TODO").description("This is a first TODO");
    }

    @GetMapping("/todo")
    @ResponseStatus(HttpStatus.OK)
    public List<Todo> retrieveAll() {
        final Todo firstTodo = new Todo().id(1L)
                .title("First TODO").description("This is a first TODO");
        final Todo secondTodo = new Todo().id(2L)
                .title("Second TODO").description("This is a second TODO");

        return Lists.newArrayList(firstTodo, secondTodo);
    }


}
