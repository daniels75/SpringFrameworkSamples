package org.daniels.spring.todo;

import org.daniels.spring.todo.domain.Todo;
import org.daniels.spring.todo.repository.TodoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class TodoApplication {
    private final Logger log = LoggerFactory.getLogger(TodoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TodoApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(TodoRepository todoRepository) {
        return args -> {
            log.info("Running a command line runner");

            List<Todo> fakeTodos = createFakeTodoList(todoRepository);
            log.info("------- Fake data ------------------------");
            fakeTodos
                .forEach(todo -> log.warn(todo.toString()));
            log.info("------------------------------------------");
        };
    }

    private List<Todo> createFakeTodoList(TodoRepository todoRepository) {
        Todo fakeTodo1 = new Todo();
        fakeTodo1.setTitle("First TODO");
        fakeTodo1.setDescription("Add real Todo REST");
        //todoRepository.save(fakeTodo1);

        Todo fakeTodo2 = new Todo();
        fakeTodo2.setTitle("Second TODO");
        fakeTodo2.setDescription("Add service");
        //todoRepository.save(fakeTodo2);

        Todo fakeTodo3 = new Todo();
        fakeTodo3.setTitle("Third TODO");
        fakeTodo3.setDescription("Add repository");
        //todoRepository.save(fakeTodo3);

        return todoRepository.findAll();
    }

}
