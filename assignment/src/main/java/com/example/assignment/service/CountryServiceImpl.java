package com.example.assignment.service;

import com.example.assignment.dto.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final Logger log = LoggerFactory.getLogger(CountryServiceImpl.class);

    @Autowired
    private WebClient webClient;

    @Override
    public Mono<List<Country>> fetchCountries() {
        Mono<List<Country>> countriesMono = webClient.get()
                .uri("all?fields=name,alpha2Code").retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Country>>() {
                }).onErrorReturn(new ArrayList<>());
        countriesMono.subscribe();
        return countriesMono;
    }

    @Override
    public Mono<Country> findCountry(String name) {
        Mono<Country> countryMono = webClient.get()
                .uri("name/" + name + "?fields=name,alpha2Code,capital,population,flag").retrieve()
                .bodyToFlux(Country.class).onErrorReturn(null).next();
        countryMono.subscribe();
        return countryMono;
    }
}
