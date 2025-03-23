package com.example.findcountrybyphonenumber.service;

import com.example.findcountrybyphonenumber.entity.Country;
import com.example.findcountrybyphonenumber.repository.FindCountryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindCountryServiceImplTest {

    @Mock
    private FindCountryRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_findCountryByPhoneNumber_Success() {
        String countryCode = "44783";
        Country expectedCountry = new Country("United Kingdom", countryCode);

        when(repository.findByCountryCode(countryCode)).thenReturn(Optional.of(expectedCountry));

        Optional<Country> actualCountry = repository.findByCountryCode(countryCode);

        assertTrue(actualCountry.isPresent());
        assertEquals(expectedCountry, actualCountry.get());
        verify(repository, times(1)).findByCountryCode(countryCode);
    }

    @Test
    void test_findCountryByPhoneNumber_CountryIsNotFound() {
        String countryCode = "";

        when(repository.findByCountryCode(countryCode)).thenReturn(null);

        Optional<Country> actualCountry = repository.findByCountryCode(countryCode);

        assertNull(actualCountry);
        verify(repository, times(1)).findByCountryCode(countryCode);
    }

    @Test
    void test_findCountryByPhoneNumber_CodeIsNull() {
        verify(repository, never()).findByCountryCode(null);
    }
}