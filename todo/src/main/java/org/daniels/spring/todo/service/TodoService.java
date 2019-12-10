package org.daniels.spring.todo.service;

import org.daniels.spring.todo.domain.Todo;
import org.daniels.spring.todo.mapper.TodoMapper;
import org.daniels.spring.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper;

    public TodoService(TodoRepository todoRepository, TodoMapper todoMapper) {
        this.todoRepository = todoRepository;
        this.todoMapper = todoMapper;
    }

    public Todo add(Todo todo) {
        Todo createdTodo =  todoRepository.save(todo);
        return createdTodo;
    }

    public List<Todo> retrieveAll() {
        List<Todo> todoList = todoRepository.findAll();
        return todoList;
    }

    public Todo update(Todo todo) {
        Todo createdTodo =  todoRepository.save(todo);
        return createdTodo;
    }

    public Todo findById(Long id) {
        Optional<Todo> todo = todoRepository.findById(id);
        return todo.get();
    }

    public void delete(Long id) {
        todoRepository.deleteById(id);
    }
}
