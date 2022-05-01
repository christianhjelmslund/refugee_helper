package com.example.workflow.transportation.services;

import com.example.workflow.transportation.models.RouteModel;
import com.example.workflow.transportation.models.RouteRequestModel;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import spinjar.com.minidev.json.JSONObject;

import javax.print.attribute.standard.Media;
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

    public String startLocationTracking(RouteRequestModel request) {
        String requestUri = REQUEST_URI_TRACKING;

        REFUGEE_APP.info("Request to be send: " + request.toString());
        JSONObject requestObject = new JSONObject();
        requestObject.put("destination", request.getDestination());
        requestObject.put("origin", request.getOrigin());
        requestObject.put("mode", request.getMode());
        requestObject.put("departure", request.getDeparture());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(requestObject.toString(), headers);

        ResponseEntity<String> sendResponse = template
                .exchange(requestUri, HttpMethod.POST, entity, String.class);

        return sendResponse.toString();
    }

}