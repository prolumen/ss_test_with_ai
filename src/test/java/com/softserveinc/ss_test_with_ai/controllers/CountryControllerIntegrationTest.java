package com.softserveinc.ss_test_with_ai.controllers;

import com.softserveinc.ss_test_with_ai.domains.Country;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CountryControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getCountries_SearchFilter() {
        ResponseEntity<List<Country>> response = restTemplate.exchange(
                "http://localhost:" + port + "/countries?query=UK",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Country>>() {}
        );

        List<Country> countries = response.getBody();

        assertNotNull(countries);
        assertTrue(countries.stream().anyMatch(country -> country.getName().getCommon().contains("Uk")));
    }

    @Test
    public void getCountries_PopulationFilter() {
        ResponseEntity<List<Country>> response = restTemplate.exchange(
                "http://localhost:" + port + "/countries?maxPopulationInMillions=100",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Country>>() {}
        );
        List<Country> countries = response.getBody();

        assertNotNull(countries);
        assertTrue(countries.stream().allMatch(country -> country.getPopulation() < 100 * 1000000));
    }

    @Test
    public void getCountries_Sorting() {
        ResponseEntity<List> response = restTemplate.getForEntity(
                "http://localhost:" + port + "/countries?order=descend",
                List.class
        );
        List<Country> countries = response.getBody();

        assertNotNull(countries);
    }

    @Test
    public void getCountries_Pagination() {
        ResponseEntity<List> response = restTemplate.getForEntity(
                "http://localhost:" + port + "/countries?limit=2",
                List.class
        );
        List<Country> countries = response.getBody();

        assertNotNull(countries);
        assertEquals(2, countries.size());
    }
}