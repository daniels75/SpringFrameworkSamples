package org.daniels.spring.tutorial.securitydemo.resource;

import com.google.common.collect.Lists;
import org.daniels.spring.tutorial.securitydemo.model.Todo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TodoResource {

    @GetMapping("/todo/{id}")
    public Todo getTodo(@PathVariable Long id) {
        final Todo fakeTodo = new Todo();
        return fakeTodo.id(id).title("First TODO").description("This is a first TODO");
    }

    @GetMapping("/todo")
    public List<Todo> retrieveAll() {
        final Todo firstTodo = new Todo().id(1L)
                .title("First TODO").description("This is a first TODO");
        final Todo secondTodo = new Todo().id(2L)
                .title("Second TODO").description("This is a second TODO");

        return Lists.newArrayList(firstTodo, secondTodo);
    }


}
