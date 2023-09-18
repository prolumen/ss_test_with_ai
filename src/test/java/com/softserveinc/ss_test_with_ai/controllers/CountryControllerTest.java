//package com.softserveinc.ss_test_with_ai.controllers;
//
//import com.softserveinc.ss_test_with_ai.domains.Country;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.when;
//
//public class CountryControllerTest {
//
//    @InjectMocks
//    private CountryController countryController;
//
//    @Mock
//    private RestTemplate restTemplate;
//
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testGetCountries() {
//        Country[] countries = new Country[1];
//        countries[0] = new Country();
//        countries[0].setName(new Country.Names());
//        countries[0].getName().setCommon("TestCountry");
//
//        when(restTemplate.getForEntity(anyString(), any())).thenReturn(ResponseEntity.ok(countries));
//
//        List<Country> result = countryController.getCountries(null, null, null, null);
//        assertEquals(1, result.size());
//        assertEquals("TestCountry", result.get(0).getName().getCommon());
//    }
//
//    @Test
//    public void testSearchCountries() {
//        Country[] countries = new Country[2];
//        countries[0] = new Country();
//        countries[0].setName(new Country.Names());
//        countries[0].getName().setCommon("TestCountry");
//
//        countries[1] = new Country();
//        countries[1].setName(new Country.Names());
//        countries[1].getName().setCommon("AnotherCountry");
//
//        when(restTemplate.getForEntity(anyString(), any())).thenReturn(ResponseEntity.ok(countries));
//
//        List<Country> result = countryController.searchCountries("Test");
//        assertEquals(1, result.size());
//        assertEquals("TestCountry", result.get(0).getName().getCommon());
//    }
//
//    @Test
//    public void testGetCountriesReturnsEmpty() {
//        Country[] countries = new Country[0];
//
//        when(restTemplate.getForEntity(anyString(), any())).thenReturn(ResponseEntity.ok(countries));
//
//        List<Country> result = countryController.getCountries(null, null, null, null);
//        assertEquals(0, result.size());
//    }
//
//    @Test
//    public void testSearchCountriesReturnsMultipleResults() {
//        Country[] countries = new Country[3];
//        countries[0] = createCountryWithName("TestCountryA");
//        countries[1] = createCountryWithName("TestCountryB");
//        countries[2] = createCountryWithName("UnrelatedCountry");
//
//        when(restTemplate.getForEntity(anyString(), any())).thenReturn(ResponseEntity.ok(countries));
//
//        List<Country> result = countryController.searchCountries("Test");
//        assertEquals(2, result.size());
//    }
//
//    @Test
//    public void testSearchCountriesCaseInsensitivity() {
//        Country[] countries = new Country[1];
//        countries[0] = createCountryWithName("testcountry");
//
//        when(restTemplate.getForEntity(anyString(), any())).thenReturn(ResponseEntity.ok(countries));
//
//        List<Country> result = countryController.searchCountries("Test");
//        assertEquals(1, result.size());
//        assertEquals("testcountry", result.get(0).getName().getCommon());
//    }
//
//    @Test
//    public void testSearchCountriesNoMatch() {
//        Country[] countries = new Country[2];
//        countries[0] = createCountryWithName("TestCountryA");
//        countries[1] = createCountryWithName("TestCountryB");
//
//        when(restTemplate.getForEntity(anyString(), any())).thenReturn(ResponseEntity.ok(countries));
//
//        List<Country> result = countryController.searchCountries("Unrelated");
//        assertEquals(0, result.size());
//    }
//
//    @Test
//    public void testExceptionHandlingWhenFetchingCountries() {
//        when(restTemplate.getForEntity(anyString(), any())).thenThrow(new RuntimeException("Unexpected error"));
//
//        assertThrows(RuntimeException.class, () -> {
//            countryController.getCountries(null, null, null, null);
//        });
//    }
//
//    private Country createCountryWithName(String commonName) {
//        Country country = new Country();
//        Country.Names name = new Country.Names();
//        name.setCommon(commonName);
//        country.setName(name);
//        return country;
//    }
//
//}
