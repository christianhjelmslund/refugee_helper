package com.example.workflow.transportation;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


@Service
public class RoutingService {

    private final RestTemplate template = new RestTemplate();
    private final String REQUEST_URI = "http://localhost/directions/simple/";

    public RouteModel getRoute(String destination, String origin, String mode, String departure) {
        String requestUri = REQUEST_URI + "?destination={destination}&origin={origin}&mode={mode}&departure={departure}";

        Map<String, String> urlParameters = new HashMap<>();
        urlParameters.put("destination", destination);
        urlParameters.put("origin", origin);
        urlParameters.put("mode", mode);
        urlParameters.put("departure", departure);
        return template.getForObject(requestUri,
                RouteModel.class,
                urlParameters);
    }

}