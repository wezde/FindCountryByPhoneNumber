package com.example.findcountrybyphonenumber.controller;

import com.example.findcountrybyphonenumber.entity.Country;
import com.example.findcountrybyphonenumber.service.FindCountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/country")
@RequiredArgsConstructor
public class FindCountryController {

    private final FindCountryService service;

    @GetMapping("/phone")
    public ResponseEntity<Country> findCountryByPhoneNumber(@RequestParam String phoneNumber) {
        String code;
        if (phoneNumber.length() > 5) {
            code = phoneNumber.substring(0, 5);
        } else code = phoneNumber;

        Country country = service.findCountryByPhoneNumber(code);
        if (country == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Страны с кодом " + code + " не найдено!");
        }
        return ResponseEntity.ok(country);
    }
}
