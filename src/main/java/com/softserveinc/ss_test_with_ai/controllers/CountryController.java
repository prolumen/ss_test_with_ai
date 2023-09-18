package com.softserveinc.ss_test_with_ai.controllers;

import com.softserveinc.ss_test_with_ai.domains.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CountryController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/countries")
    public List<Country> getCountries(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) Long maxPopulationInMillions,
            @RequestParam(required = false) String order,
            @RequestParam(required = false) Integer count
    ) {
        List<Country> countries = fetchAllCountries();

        if (query != null) {
            countries = searchCountries(countries, query);
        }

        if (maxPopulationInMillions != null) {
            countries = filterByPopulation(countries, maxPopulationInMillions);
        }

        if (order != null) {
            countries = sortByName(countries, order);
        }

        if (count != null) {
            countries = limitCountries(countries, count);
        }

        return countries;
    }

    private List<Country> fetchAllCountries() {
        ResponseEntity<Country[]> response = restTemplate.getForEntity("https://restcountries.com/v3.1/all", Country[].class);
        return Arrays.asList(response.getBody());
    }

    private List<Country> searchCountries(List<Country> countries, String query) {
        return countries.stream()
                .filter(country -> country.getName().getCommon().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    private List<Country> filterByPopulation(List<Country> countries, long maxPopulationInMillions) {
        return countries.stream()
                .filter(country -> country.getPopulation() < maxPopulationInMillions * 1000000)
                .collect(Collectors.toList());
    }

    private List<Country> sortByName(List<Country> countries, String order) {
        Comparator<Country> comparator = Comparator.comparing(country -> country.getName().getCommon().toLowerCase());

        if ("descend".equals(order)) {
            comparator = comparator.reversed();
        }

        return countries.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    private List<Country> limitCountries(List<Country> countries, int count) {
        return countries.stream()
                .limit(count)
                .collect(Collectors.toList());
    }
}
