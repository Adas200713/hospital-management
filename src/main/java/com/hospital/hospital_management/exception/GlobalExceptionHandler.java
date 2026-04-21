package com.hospital.hospital_management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //  Handle conflict
    @ExceptionHandler(TimeSlotConflictException.class)
    public ResponseEntity<String> handleConflict(TimeSlotConflictException ex) {
        System.out.println("CONFLICT HANDLER HIT");
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    // Fallback (generic error)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneric(Exception ex) {
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}