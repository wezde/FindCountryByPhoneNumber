package com.example.findcountrybyphonenumber.service;

import com.example.findcountrybyphonenumber.entity.Country;
import com.example.findcountrybyphonenumber.repository.FindCountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class FindCountryServiceImpl implements FindCountryService {

    private final FindCountryRepository repository;

    @Override
    public Map<String, List<String>> findCountryByPhoneNumber(String countryCode) {
        System.out.println(countryCode);
        List<Country> countries = repository.findByCountryCode(countryCode);
        System.out.println(countries);
        Map<String, List<String>> codeToCountries = new HashMap<>();
        for (Country country : countries) {
            codeToCountries
                    .computeIfAbsent(country.getCountryCode(), k -> new ArrayList<>())
                    .add(country.getCountryName());
        }
        return codeToCountries;
    }
}
