package com.example.workflow.services;

import com.example.workflow.models.CountriesModel;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class CountryService {
    private final RestTemplate template = new RestTemplate();

    public CountriesModel getAllCountries() {
        return template.getForObject("http://localhost/countries", CountriesModel.class);
    }

}
