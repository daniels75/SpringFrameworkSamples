package org.daniels.spring.todo.service;

import org.daniels.spring.todo.domain.Todo;
import org.daniels.spring.todo.mapper.TodoMapper;
import org.daniels.spring.todo.model.TodoDTO;
import org.daniels.spring.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper;

    public TodoService(TodoRepository todoRepository, TodoMapper todoMapper) {
        this.todoRepository = todoRepository;
        this.todoMapper = todoMapper;
    }

    public TodoDTO add(TodoDTO todoDTO) {
        Todo createdTodo =  todoRepository.save(todoMapper.todoDTOToTodo(todoDTO));

        return todoMapper.todoToTodoDTO(createdTodo);
    }

    public List<TodoDTO> retrieveAll() {
        List<TodoDTO> todoList = todoRepository.findAll()
                .stream()
                .map(todoMapper::todoToTodoDTO)
                .collect(Collectors.toList());
        return todoList;
    }

    public TodoDTO update(TodoDTO todoDTO) {
        Todo updatedTodo =  todoRepository.save(todoMapper.todoDTOToTodo(todoDTO));

        return todoMapper.todoToTodoDTO(updatedTodo);
    }

    public TodoDTO findById(Long id) {
        Optional<Todo> todo = todoRepository.findById(id);

        return todoMapper.todoToTodoDTO(todo.get());
    }

    public void delete(Long id) {
        todoRepository.deleteById(id);
    }
}
