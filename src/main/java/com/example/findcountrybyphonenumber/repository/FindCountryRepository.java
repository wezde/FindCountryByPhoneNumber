package com.example.findcountrybyphonenumber.repository;

import com.example.findcountrybyphonenumber.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FindCountryRepository extends JpaRepository<Country, Long> {

    @Query(value = "SELECT * FROM country WHERE country_code = :countryCode", nativeQuery = true)
    Optional<Country> findByCountryCode(String countryCode);
}
