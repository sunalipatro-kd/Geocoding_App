package com.kdu.caching.service;

import com.kdu.caching.entity.GeoCodingResponse;
import com.kdu.caching.entity.Location;
import com.kdu.caching.exception.GeoCodingException;
import com.kdu.caching.exception.InvalidInputException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Service interface for geocoding and reverse geocoding operations.
 */
@Service
public class GeoCodingServiceImpl implements GeoCodingService {
    @Value("${positionstack.api.key}")
    private String apiKey;
    private static final Logger logger = LoggerFactory.getLogger(GeoCodingServiceImpl.class);


    /**
     * Retrieves geocoding information for the specified address.
     *
     * @param address the address to be geocoded.
     * @return the {@link Location} containing latitude, longitude, and other details of the specified address.
     */
    @Override
    @Cacheable(value = "geocoding", key = "#address", condition = "!#address.equalsIgnoreCase('goa')")
    public Location getGeoCode(String address) {
        logger.info("Fetching geocoding data for address: {}", address);

        if (address == null || address.isBlank()) {
            throw new InvalidInputException("Address cannot be null or empty");
        }
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://api.positionstack.com/v1/forward?access_key="+apiKey+"&query=" + address;
        GeoCodingResponse response;
        response = restTemplate.getForObject(url, GeoCodingResponse.class);
        if (response == null || response.getData() == null || response.getData().isEmpty()) {
            logger.error("Invalid response from geocoding API for address: {}", address);
            throw new InvalidInputException("No location data found for address: ");
        }
        Location location = response.getData().get(0);
        logger.info("Geocoding result: {}", location);
        return location;
    }


    /**
     * Retrieves the address information based on the provided latitude and longitude.
     *
     * @param latitude  the latitude coordinate.
     * @param longitude the longitude coordinate.
     * @return the address as a {@link String} for the specified coordinates.
     */
    @Override
    @Cacheable(value = "reverse-geocoding", key = "{#latitude,#longitude}")
    public String getReverseGeoCode(Double latitude, Double longitude) {
        logger.info("Fetching reverse geocoding data for lat: {} long: {}", latitude, longitude);

        if (latitude == null || longitude == null) {
            throw new InvalidInputException("Latitude and longitude cannot be null");
        }
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://api.positionstack.com/v1/reverse?access_key=" + apiKey + "&query=" + latitude + "," + longitude;
        GeoCodingResponse response = restTemplate.getForObject(url, GeoCodingResponse.class);

        if (response == null || response.getData() == null || response.getData().isEmpty()) {
            logger.error("Invalid response from reverse geocoding API");
            throw new GeoCodingException("No address data found for latitude: " + latitude + ", longitude: " + longitude);
        }

        Location location = response.getData().get(0);
        String address = String.format("%s %s", location.getName(),location.getCountry());
        logger.info("Reverse geocoding result: {}", address);
        return address;
    }
}