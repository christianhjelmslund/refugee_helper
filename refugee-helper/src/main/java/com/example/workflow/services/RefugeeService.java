package com.example.workflow.services;

import com.example.workflow.models.CountriesModel;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.example.workflow.CheckUserInfo.REFUGEE_APP;


@Service
public class RefugeeService {
    private final RestTemplate template = new RestTemplate();

    public int getNumberOfRefugees(String country) {
        REFUGEE_APP.info("Number of refugees:" + template.getForObject("http://127.0.0.1:5000/" + country, String.class));
        int number_of_refugees = Integer.parseInt(template.getForObject("http://127.0.0.1:5000/" + country, String.class));
        return number_of_refugees;
    }

}
