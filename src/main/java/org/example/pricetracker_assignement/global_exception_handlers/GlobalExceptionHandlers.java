package org.example.pricetracker_assignement.global_exception_handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.FileNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandlers {

    @ExceptionHandler
    private ResponseEntity<String> fileNotFoundExceptionHandler(FileNotFoundException fileNotFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(fileNotFoundException.getMessage());
    }

    @ExceptionHandler
    private ResponseEntity<String> generalExceptionHandler(Exception exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
}
