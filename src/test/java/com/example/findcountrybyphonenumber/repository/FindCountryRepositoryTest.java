package com.example.findcountrybyphonenumber.repository;

import com.example.findcountrybyphonenumber.entity.Country;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class FindCountryRepositoryTest {

    @Autowired
    FindCountryRepository repository;

    @BeforeEach
    @Rollback(value = false)
    void setUp() {
        Country country = new Country();
        country.setCountryCode("+1");
        country.setCountryName("United States");
        repository.save(country);
    }

    @Test
    void test_findByCountryCode_Success() {
        Optional<Country> result = repository.findByCountryCode("+1");

        assertThat(result).isPresent();
        assertThat(result.get().getCountryCode()).isEqualTo("+1");
        assertThat(result.get().getCountryName()).isEqualTo("United States");
    }

    @Test
    void test_findByCountryCode_NotFound() {
        Optional<Country> result = repository.findByCountryCode("+2");

        assertThat(result).isNotPresent();
    }
}