package com.kdu.caching.service;

import com.kdu.caching.entity.Location;


/**
 * Service interface for geocoding and reverse geocoding operations.
 */
public interface GeoCodingService {
    Location getGeoCode(String address);
    String getReverseGeoCode(Double latitude, Double longitude);
}