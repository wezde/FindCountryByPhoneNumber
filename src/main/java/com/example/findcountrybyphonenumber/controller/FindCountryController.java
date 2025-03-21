package com.example.findcountrybyphonenumber.controller;

import com.example.findcountrybyphonenumber.service.FindCountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/country")
@RequiredArgsConstructor
public class FindCountryController {

    private final FindCountryService service;

    @GetMapping("/phone")
    public ResponseEntity<?> findCountryByPhoneNumber(@RequestParam String phoneNumber) {
        if (!phoneNumber.matches("^\\+\\d{1,3} \\d{6,15}$")) {
            return ResponseEntity.badRequest().body("Неверный формат номера! Пример: +7 9123456789");
        }

        String code = phoneNumber.split(" ")[0];

        Map<String, List<String>> countries = service.findCountryByPhoneNumber(code);
        if (countries.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "Страны с кодом " + code + " не найдены!");
        }
        return ResponseEntity.ok(countries);
    }
}
