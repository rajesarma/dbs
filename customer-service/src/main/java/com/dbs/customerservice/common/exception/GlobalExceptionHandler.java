package com.dbs.customerservice.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
//@RequestMapping(produces = "application/vnd.error+json")
public class GlobalExceptionHandler {

    @ExceptionHandler
	public ResponseEntity<String> handleInvalidHeaderFieldException(NullPointerException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.PRECONDITION_FAILED);
	}

    @ExceptionHandler
    public ResponseEntity<String> handleIOException(IOException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.PRECONDITION_FAILED);
    }
}
