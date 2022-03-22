package com.example.assignment.service;

import com.example.assignment.dto.Country;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Provider for fetching countries information stored in an external service.
 */
public interface CountryService {

    /**
     * Fetch all countries from external service.
     *
     * @return
     */
    Mono<List<Country>> fetchCountries();

    /**
     * Fetch country by name from external service.
     *
     * @param name country name
     * @return first country that matches the given name
     */
    Mono<Country> findCountry(String name);
}
