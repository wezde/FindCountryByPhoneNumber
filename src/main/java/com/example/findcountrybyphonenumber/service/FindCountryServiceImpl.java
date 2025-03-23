package com.example.findcountrybyphonenumber.service;

import com.example.findcountrybyphonenumber.entity.Country;
import com.example.findcountrybyphonenumber.repository.FindCountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindCountryServiceImpl implements FindCountryService {

    private final FindCountryRepository repository;

    @Override
    public Country findCountryByPhoneNumber(String countryCode) {
        if (countryCode == null || countryCode.isEmpty()) {
            return null;
        }
        int codeLength = countryCode.length();
        for (int i = codeLength; i > 0; i--) {
            String code = countryCode.substring(0, i);
            Optional<Country> country = repository.findByCountryCode(code);

            if (country.isPresent()) {
                return country.get();
            }
        }
        return null;
    }
}
