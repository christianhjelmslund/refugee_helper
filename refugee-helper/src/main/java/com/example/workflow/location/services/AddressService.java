package com.example.workflow.location.services;

import com.example.workflow.location.models.AddressModel;
import com.example.workflow.location.models.AddressRequestModel;
import com.example.workflow.models.CountriesModel;
import com.example.workflow.transportation.models.RouteRequestModel;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.example.workflow.CheckUserInfo.REFUGEE_APP;

@Service
public class AddressService {
    private final RestTemplate template = new RestTemplate();
    private String REQUEST_URI_ADDRESS = "http://localhost/address/";

    public String getAddress(float lat, float lng) {
        AddressRequestModel request = new AddressRequestModel();
        request.setLat(lat);
        request.setLng(lng);
        return template.postForObject(REQUEST_URI_ADDRESS, request,
                String.class);
    }
}
