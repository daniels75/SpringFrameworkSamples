package org.daniels.spring.todo.rest.v1.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TitleNotProvidedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public TitleNotProvidedException(String exception) {
        super(exception);
    }
}
