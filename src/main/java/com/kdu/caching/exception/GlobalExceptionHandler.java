package com.kdu.caching.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.Map;

/**
 * Global exception handler to handle application-specific exceptions and provide meaningful error responses.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {


    /**
     * Handles {@link InvalidInputException} and returns a structured error response with HTTP 400 status.
     *
     * @param ex the exception object containing the details of the invalid input error.
     * @return a {@link ResponseEntity} containing the error message and details.
     */
    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<Map<String, String>> handleInvalidInputException(InvalidInputException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                        "error", "Invalid input",
                        "message", ex.getMessage()
                ));
    }

    /**
     * Handles {@link GeoCodingException} and returns a structured error response with HTTP 400 status.
     *
     * @param ex the exception object containing the details of the geocoding error.
     * @return a {@link ResponseEntity} containing the error message and details.
     */
    @ExceptionHandler(GeoCodingException.class)
    public ResponseEntity<Map<String, String>> handleGeoCodingException(GeoCodingException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                        "error", "GeoCoding error",
                        "message", ex.getMessage()
                ));
    }
}
