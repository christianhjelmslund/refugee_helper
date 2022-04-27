package com.example.workflow.transportation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class CityService {

    private final RestTemplate template = new RestTemplate();

    public CitiesModel findAllCitiesByCountryName(String name) {
        return template.getForObject("http://localhost/country/name/Germany/cities", CitiesModel.class);
    }

}
