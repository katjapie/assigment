package com.example.assignment.service;

import com.example.assignment.dto.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
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
        try {
            Mono<List<Country>> countriesMono = webClient.get()
                    .uri("all?fields=name,alpha2Code").retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<Country>>() {
                    });
            countriesMono.subscribe();
            return countriesMono;
        } catch (WebClientException e) {
            log.error("Error in fetching countries: ", e);
        }
        return Mono.just(new ArrayList<>());
    }

    @Override
    public Mono<List<Country>> findCountry(String name) {
        try {
            Mono<List<Country>> countriesMono = webClient.get()
                    .uri("name/" + name + "?fields=name,alpha2Code,capital,population,flag").retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<Country>>() {
                    });
            countriesMono.subscribe();
            return countriesMono;
        } catch (WebClientException e) {
            log.error("Error in finding country: ", e);
        }
        return Mono.just(new ArrayList<>());
    }
}
