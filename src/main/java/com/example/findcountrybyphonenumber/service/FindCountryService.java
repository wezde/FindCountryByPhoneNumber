package com.example.findcountrybyphonenumber.service;

import com.example.findcountrybyphonenumber.entity.Country;

public interface FindCountryService {

    Country findCountryByPhoneNumber(String phoneNumber);
}
