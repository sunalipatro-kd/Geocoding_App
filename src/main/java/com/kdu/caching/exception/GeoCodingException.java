package com.kdu.caching.exception;

/**
 * Custom exception to handle errors related to geocoding operations.
 */
public class GeoCodingException extends RuntimeException {
    public GeoCodingException(String message) {
        super(message);
    }

}
