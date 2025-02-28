package com.kdu.caching;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Main class for the GeoCoding application.
 * <p>
 * This application provides endpoints for geocoding and reverse geocoding
 * with caching capabilities.
 * </p>
 */
@SpringBootApplication
@EnableCaching
public class GeoCodingApplication {
    public static void main(String[] args) {
        SpringApplication.run(GeoCodingApplication.class, args);
    }
}