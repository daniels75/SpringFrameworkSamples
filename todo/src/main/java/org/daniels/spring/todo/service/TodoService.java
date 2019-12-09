package org.daniels.spring.todo.service;

import org.daniels.spring.todo.domain.Todo;
import org.daniels.spring.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo add(Todo todo) {
        Todo createdTodo =  todoRepository.save(todo);
        return createdTodo;
    }

    public List<Todo> retrieveAll() {
        List<Todo> todoList = todoRepository.findAll();
        return todoList;
    }

}
