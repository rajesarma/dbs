package com.dbs.creditcardservice.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
	public ResponseEntity<String> handleNullPointerException(NullPointerException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.PRECONDITION_FAILED);
	}

	@ExceptionHandler
	public ResponseEntity<String> handleIOException(IOException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.PRECONDITION_FAILED);
	}

	@ExceptionHandler
	public ResponseEntity<String> handleNumberException(NumberFormatException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.PRECONDITION_FAILED);
	}
}
