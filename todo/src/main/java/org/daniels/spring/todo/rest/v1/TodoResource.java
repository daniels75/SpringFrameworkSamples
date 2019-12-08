package org.daniels.spring.todo.rest.v1;

import com.google.common.collect.Lists;
import org.daniels.spring.todo.domain.Todo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TodoResource {

    @GetMapping("/todos")
    @ResponseStatus(HttpStatus.OK)
    public List<Todo> retrieveAll() {
        final List<Todo> list = createFakeTodoList();
        return list;
    }

    private List<Todo> createFakeTodoList() {
        Todo fakeTodo1 = new Todo();
        fakeTodo1.setId(1L);
        fakeTodo1.setTitle("First TODO");
        fakeTodo1.setDescription("Add real Todo REST");
        fakeTodo1.complete(false);
        Todo fakeTodo2 = new Todo();
        fakeTodo2.setId(2L);
        fakeTodo2.setTitle("Second TODO");
        fakeTodo2.setDescription("Add service");
        fakeTodo2.complete(false);
        Todo fakeTodo3 = new Todo();
        fakeTodo3.setId(3L);
        fakeTodo3.setTitle("Third TODO");
        fakeTodo3.setDescription("Add repository");
        fakeTodo3.complete(false);
        return Lists.newArrayList(fakeTodo1, fakeTodo2, fakeTodo3);
    }
}
