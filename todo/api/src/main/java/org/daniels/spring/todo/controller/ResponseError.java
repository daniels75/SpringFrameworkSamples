package org.daniels.spring.todo.controller;

import com.google.common.collect.Lists;

import java.util.Date;
import java.util.List;

public class ResponseError {
    private Date timestamp;
    private List<ErrorDetail> errors = Lists.newArrayList();

    public ResponseError timestamp(Date timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public ResponseError errorDetails(ErrorDetail errorDetail) {
        this.errors.add(errorDetail);
        return this;
    }

    public ResponseError errorDetails(List<ErrorDetail> errorDetails) {
        this.errors.addAll(errorDetails);
        return this;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public List<ErrorDetail> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorDetail> errors) {
        this.errors = errors;
    }
}
