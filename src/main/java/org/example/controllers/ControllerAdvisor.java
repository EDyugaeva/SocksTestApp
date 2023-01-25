package org.example.controllers;

import org.example.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.webjars.NotFoundException;

import java.io.IOException;

@RestControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundException(NotFoundException notFoundException) {
        return ErrorResponse.builder()
                .message(notFoundException.getMessage())
                .httpStatus(HttpStatus.NOT_FOUND)
                .build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleIllegalArgumentException(IllegalArgumentException illegalArgumentException) {
        return ErrorResponse.builder()
                .message(illegalArgumentException.getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .build();
    }

    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleIOException(IOException ioException) {
        return ErrorResponse.builder()
                .message(ioException.getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .build();
    }


    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleNullPointerException(NullPointerException nullPointerException) {
        return ErrorResponse.builder()
                .message(nullPointerException.getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .build();
    }
}
