package com.dbs.customerservice.common.exception;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * GlobalExceptionHandler
 *
 * Component class for Global Exception
 *
 * @author lakshmirajeswararao.p
 * */

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * This method handles NullPointerException returns a Response Entity of the message
     *
     * @param ex NullPointerException
     * @return ResponseEntity
     */
    @ExceptionHandler
	public ResponseEntity<String> handleInvalidHeaderFieldException(NullPointerException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.PRECONDITION_FAILED);
	}

    /**
     * This method handles IOException returns a Response Entity of the message
     *
     * @param ex IOException
     * @return ResponseEntity
     */
    @ExceptionHandler
    public ResponseEntity<String> handleIOException(IOException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.PRECONDITION_FAILED);
    }
}
