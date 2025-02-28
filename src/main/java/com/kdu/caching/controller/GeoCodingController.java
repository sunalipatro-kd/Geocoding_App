package com.kdu.caching.controller;

import com.kdu.caching.entity.Location;
import com.kdu.caching.service.GeoCodingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling geocoding and reverse geocoding API requests.
 */
@RestController

public class GeoCodingController {
    @Autowired
    private GeoCodingService geoCodingService;

    /**
     * Endpoint to fetch geocoding information for a given address.
     *
     * @param address the address for which geocoding information is required.
     * @return the {@link Location} object containing the geocoding details for the provided address.
     */
    @GetMapping("/geocoding")
    public Location getGeoCode(@RequestParam String address) {
        return geoCodingService.getGeoCode(address);
    }

    /**
     * Endpoint to fetch reverse geocoding information for a given latitude and longitude.
     *
     * @param latitude  the latitude of the location.
     * @param longitude the longitude of the location.
     * @return a {@link String} representing the address corresponding to the given latitude and longitude.
     */
    @GetMapping("/reverse-geocoding")
    public String getReverseGeoCode(@RequestParam Double latitude, @RequestParam Double longitude) {
        return geoCodingService.getReverseGeoCode(latitude, longitude);
    }
}