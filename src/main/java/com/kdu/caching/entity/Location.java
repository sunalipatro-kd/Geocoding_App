package com.kdu.caching.entity;

import lombok.Getter;

/**
 * Represents a location with geocoding details such as latitude, longitude, and other metadata.
 */
@Getter
public class Location {
    private Double latitude;
    private Double longitude;
    private String name;
    private String type;
    private Double confidence;
    private String region;
    private String country;
}


