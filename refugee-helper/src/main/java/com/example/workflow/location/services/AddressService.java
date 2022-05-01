package com.example.workflow.location.services;

import com.example.workflow.location.models.AddressModel;
import com.example.workflow.location.models.AddressRequestModel;
import com.example.workflow.models.CountriesModel;
import com.example.workflow.transportation.models.RouteRequestModel;
import org.camunda.spin.plugin.variable.SpinValues;
import org.camunda.spin.plugin.variable.value.JsonValue;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import spinjar.com.minidev.json.JSONObject;

import static com.example.workflow.CheckUserInfo.REFUGEE_APP;

@Service
public class AddressService {
    private final RestTemplate template = new RestTemplate();
    private String REQUEST_URI_ADDRESS = "http://localhost/address/";

    public JsonValue getAddress(float lat, float lng) {

        JSONObject requestObject = new JSONObject();
        requestObject.put("lat", lat);
        requestObject.put("lng", lng);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(requestObject.toString(), headers);

        ResponseEntity<String> sendResponse = template
                .exchange(REQUEST_URI_ADDRESS, HttpMethod.POST, entity, String.class);
        JsonValue jsonValue = SpinValues.jsonValue(sendResponse.toString()).create();
        return jsonValue;
    }
}
