package org.daniels.spring.todo.mapper;

import org.daniels.spring.todo.domain.Todo;
import org.daniels.spring.todo.model.TodoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TodoMapper {
    TodoMapper INSTANCE = Mappers.getMapper(TodoMapper.class);

    TodoDTO todoToTodoDTO(Todo todo);

    Todo todoDTOToTodo(TodoDTO todoDTO);
}
