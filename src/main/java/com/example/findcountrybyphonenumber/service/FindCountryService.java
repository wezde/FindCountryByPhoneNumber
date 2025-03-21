package com.example.findcountrybyphonenumber.service;

import java.util.List;
import java.util.Map;

public interface FindCountryService {

    Map<String, List<String>> findCountryByPhoneNumber(String phoneNumber);
}
