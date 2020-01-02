package org.daniels.spring.todo.controller;

import com.google.common.collect.Lists;

import java.util.Date;
import java.util.List;

public class ResponseError {
    private Date timestamp;
    private List<ErrorDetails> errors = Lists.newArrayList();

    public ResponseError timestamp(Date timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public ResponseError errorDetails(ErrorDetails errorDetails) {
        this.errors.add(errorDetails);
        return this;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public List<ErrorDetails> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorDetails> errors) {
        this.errors = errors;
    }
}
