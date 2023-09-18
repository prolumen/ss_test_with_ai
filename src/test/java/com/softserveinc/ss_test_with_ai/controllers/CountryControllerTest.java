package com.softserveinc.ss_test_with_ai.controllers;

import com.softserveinc.ss_test_with_ai.domains.Country;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CountryControllerTest {
    private Country country1;
    private Country country2;

    @InjectMocks
    private CountryController countryController;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        country1 = new Country("CountryA", 5000000L);
        country2 = new Country("CountryB", 7000000L);
    }

    @Test
    public void testGetCountries() {
        // Given
        Country[] countries = {country1, country2};

        when(restTemplate.getForEntity("https://restcountries.com/v3.1/all", Country[].class))
                .thenReturn(ResponseEntity.ok(countries));

        // When
        List<Country> result = countryController.getCountries("CountryA", null, "ascend", null);

        // Then
        assertEquals(1, result.size());
        assertEquals("CountryA", result.get(0).getName().getCommon());
    }

    @Test
    public void testGetCountries_PopulationFilter() {
        // Given
        Country[] countries = {country1, country2};

        when(restTemplate.getForEntity("https://restcountries.com/v3.1/all", Country[].class))
                .thenReturn(ResponseEntity.ok(countries));

        // When
        List<Country> result = countryController.getCountries(null, 5000000L, null, null);

        // Then
        assertEquals(2, result.size());
        assertEquals(5000000, result.get(0).getPopulation());
    }

    @Test
    public void testGetCountries_Sorting() {
        // Given
        Country[] countries = {country1, country2};

        when(restTemplate.getForEntity("https://restcountries.com/v3.1/all", Country[].class))
                .thenReturn(ResponseEntity.ok(countries));

        // When
        List<Country> result = countryController.getCountries(null, null, "descend", null);

        // Then
        assertEquals(2, result.size());
        assertEquals("CountryB", result.get(0).getName().getCommon());
        assertEquals("CountryA", result.get(1).getName().getCommon());
    }

    @Test
    public void testGetCountries_Pagination() {
        // Given
        Country[] countries = {country1, country2};

        when(restTemplate.getForEntity("https://restcountries.com/v3.1/all", Country[].class))
                .thenReturn(ResponseEntity.ok(countries));

        // When
        List<Country> result = countryController.getCountries(null, null, null, 1);

        // Then
        assertEquals(1, result.size());
        assertEquals("CountryA", result.get(0).getName().getCommon());
    }
}
