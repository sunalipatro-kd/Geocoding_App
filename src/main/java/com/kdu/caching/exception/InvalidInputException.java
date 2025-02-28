package com.kdu.caching.exception;

/**
 * Custom exception thrown when invalid input is provided to the application.
 */
public class InvalidInputException extends RuntimeException {

    public InvalidInputException(String message) {
        super(message);
    }
}
