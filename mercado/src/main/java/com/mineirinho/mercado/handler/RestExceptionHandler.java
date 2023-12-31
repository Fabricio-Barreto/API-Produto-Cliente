package com.mineirinho.mercado.handler;

import com.mineirinho.mercado.model.error.ErrorMessage;
import com.mineirinho.mercado.model.exception.BadRequestException;
import com.mineirinho.mercado.model.exception.InternalServerErrorException;
import com.mineirinho.mercado.model.exception.InvalidJwtAuthenticationException;
import com.mineirinho.mercado.model.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorMessage error = new ErrorMessage("Not Found", HttpStatus.NOT_FOUND.value(),ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequestException(BadRequestException ex) {
        ErrorMessage error = new ErrorMessage("Bad Request", HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<?> handleInternalServerErrorException(InternalServerErrorException ex) {
        ErrorMessage error = new ErrorMessage("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidJwtAuthenticationException.class)
    public ResponseEntity<?> handleInvalidJwtAuthenticationException(InternalServerErrorException ex) {
        ErrorMessage error = new ErrorMessage("Internal Server Error", HttpStatus.FORBIDDEN.value(), ex.getMessage());
            return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }
}
