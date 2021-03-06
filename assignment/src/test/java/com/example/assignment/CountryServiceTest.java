package com.example.assignment;

import com.example.assignment.dto.Country;
import com.example.assignment.service.CountryServiceImpl;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class CountryServiceTest {

    @Mock
    private WebClient webClientMock;

    @Mock
    private WebClient.RequestHeadersSpec requestHeadersMock;

    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriMock;

    @Mock
    private WebClient.ResponseSpec responseMock;

    @InjectMocks
    private CountryServiceImpl countryService = new CountryServiceImpl();

    @Test
    public void fetchCountriesTest() {
        Country country1 = new Country() {{
            setName("Finland");
            setAlpha2Code("FI");
        }};

        Country country2 = new Country() {{
            setName("Norway");
            setAlpha2Code("NO");
        }};

        List<Country> countryList = Arrays.asList(country1, country2);

        when(webClientMock.get()).thenReturn(requestHeadersUriMock);
        when(requestHeadersUriMock.uri("all?fields=name,alpha2Code")).thenReturn(requestHeadersMock);
        when(requestHeadersMock.retrieve()).thenReturn(responseMock);
        when(responseMock.bodyToMono(new ParameterizedTypeReference<List<Country>>() {
        })).thenReturn(Mono.just(countryList));

        List<Country> countriesResponse = countryService.fetchCountries().block();
        Assert.assertEquals("Wrong length of country list", countryList.size(), countriesResponse.size());
    }

    @Test
    public void findCountryTest() {
        String name = "Finland";
        Country country = new Country() {{
            setName(name);
            setAlpha2Code("FI");
            setCapital("Helsinki");
            setPopulation(5530719);
        }};

        when(webClientMock.get()).thenReturn(requestHeadersUriMock);
        when(requestHeadersUriMock.uri("name/" + name + "?fields=name,alpha2Code,capital,population,flag")).thenReturn(requestHeadersMock);
        when(requestHeadersMock.retrieve()).thenReturn(responseMock);
        when(responseMock.bodyToFlux(Country.class)).thenReturn(Flux.just(country));

        Country countryResponse = countryService.findCountry(name).block();
        Assert.assertEquals(country, countryResponse);
    }
}
