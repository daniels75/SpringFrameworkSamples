package org.daniels.spring.todo.rest.v1.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TitleNotValidException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public TitleNotValidException(String exception) {
        super(exception);
    }
}
