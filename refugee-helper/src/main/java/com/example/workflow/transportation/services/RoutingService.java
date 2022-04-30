package com.example.workflow.transportation.services;

import com.example.workflow.transportation.models.RouteModel;
import com.example.workflow.transportation.models.RouteRequestModel;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static com.example.workflow.CheckUserInfo.REFUGEE_APP;


@Service
public class RoutingService {

    private final RestTemplate template = new RestTemplate();
    private final String REQUEST_URI = "http://localhost/directions/simple/";
    private final String REQUEST_URI_TRACKING = "http://localhost/tracking/start/";

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

    public String startLocationTracking(String destination, String origin, String mode, String departure) {
        String requestUri = REQUEST_URI_TRACKING;

        RouteRequestModel request = new RouteRequestModel();
        request.setDestination(destination);
        request.setOrigin(origin);
        request.setMode(mode);
        request.setDeparture(departure);
        REFUGEE_APP.info("Request to be send: " + request);

        return template.postForObject(requestUri, request,
                String.class);
    }

}