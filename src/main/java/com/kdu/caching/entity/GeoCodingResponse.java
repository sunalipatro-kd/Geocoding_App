package com.kdu.caching.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Represents the response received from the geocoding API.
 * Contains a list of {@link Location} objects that provide geocoding details.
 */
@Setter
@Getter
public class GeoCodingResponse {
    private List<Location> data;
}