package com.example.findcountrybyphonenumber.controller;

import com.example.findcountrybyphonenumber.entity.Country;
import com.example.findcountrybyphonenumber.service.FindCountryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FindCountryController.class)
class FindCountryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FindCountryService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_findCountryByPhoneNumber_Success() throws Exception {
        String phoneNumber = "44783492039";
        String code = "44783";
        Country expectedCountry = new Country("United Kingdom", code);

        when(service.findCountryByPhoneNumber(code)).thenReturn(expectedCountry);

        mockMvc.perform(get("/country/phone")
                        .param("phoneNumber", phoneNumber)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.countryName").value("United Kingdom"))
                .andExpect(jsonPath("$.countryCode").value("44783"));

        verify(service, times(1)).findCountryByPhoneNumber(code);
    }

    @Test
    void test_findCountryByPhoneNumber_NotFound() throws Exception {
        String phoneNumber = "123456789";
        String code = "12345";

        when(service.findCountryByPhoneNumber(code)).thenReturn(null);

        mockMvc.perform(get("/country/phone")
                        .param("phoneNumber", phoneNumber)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(status().reason("Страны с кодом " + code + " не найдено!"));

        verify(service, times(1)).findCountryByPhoneNumber(code);
    }
}