package org.daniels.spring.todo.controller;


import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.daniels.spring.todo.rest.v1.errors.TitleNotValidException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.List;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        List<ErrorDetail> errorDetails = Lists.newArrayList();
        if (ex.getBindingResult() != null && CollectionUtils.isNotEmpty(ex.getBindingResult().getAllErrors())){
            for (ObjectError objectError : ex.getBindingResult().getAllErrors()) {
                ErrorDetail errorDetail = new ErrorDetail("Validation Failed",
                        objectError.getDefaultMessage());
                errorDetails.add(errorDetail);
            }
        }

        ResponseError responseError = new ResponseError()
                .timestamp(new Date())
                .errorDetails(errorDetails);
        return new ResponseEntity(responseError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail(ex.getMessage(),
                request.getDescription(false));
        ResponseError responseError = new ResponseError()
                .timestamp(new Date())
                .errorDetails(errorDetail);

        return new ResponseEntity(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TitleNotValidException.class)
    public final ResponseEntity<ResponseError> handleUserNotFoundException(TitleNotValidException ex, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail("Validation Failed",
                ex.getMessage());
        ResponseError responseError = new ResponseError()
                .timestamp(new Date())
                .errorDetails(errorDetail);

        return new ResponseEntity<>(responseError, HttpStatus.NOT_FOUND);
    }

}
