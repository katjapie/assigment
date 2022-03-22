package com.example.assignment;

import com.example.assignment.dto.Country;
import com.example.assignment.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping(value = "/countries", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<List<Country>> fetchCountries() {
        return countryService.fetchCountries();
    }

    @GetMapping(value = "/country/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Country> fetchCountry(@PathVariable String name) {
        return countryService.findCountry(name);
    }
}
