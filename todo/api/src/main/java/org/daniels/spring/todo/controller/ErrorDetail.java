package org.daniels.spring.todo.controller;

public class ErrorDetail {
    private String message;
    private String details;

    public ErrorDetail(String message, String details) {
        this.message = message;
        this.details = details;
    }

    public String getMessage() {
        return message;
    }
    public String getDetails() {
        return details;
    }
}
