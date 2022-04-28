package com.example.workflow.transportation;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;

import static com.example.workflow.CheckUserInfo.REFUGEE_APP;
import static org.camunda.bpm.engine.variable.Variables.objectValue;

@Component
public class GetRoute implements JavaDelegate {

    @Autowired
    private RoutingService routingService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String destination_city = (String) execution.getVariable("SELECTED_CITY");
        String current_address = (String) execution.getVariable("CURRENT_ADDRESS");
        String mode = (String) execution.getVariable("SELECTED_TRANS_MODE");
        REFUGEE_APP.info("Selected City is: " + destination_city + " and the current location is: " + current_address);
        String departure_date = (String) execution.getVariable("DATE");
        String departure_time = (String) execution.getVariable("TIME");
        String departure_time_complete;
        if(departure_date == null){
            departure_time_complete = "now";
        }else{
            LocalDateTime localTime = LocalDateTime.parse(departure_date + "T" + departure_time + ":00");
            long timeInSeconds = localTime.toEpochSecond(ZoneOffset.UTC);
            departure_time_complete = Long.toString(timeInSeconds);
        }
        REFUGEE_APP.info("Destination City: " + destination_city + " ," + "Current Address: " + current_address + " ," +"Mode: " + mode + " ," +"Departure Time: " + departure_time_complete);
        RouteModel route = routingService.getRoute(destination_city, current_address, mode, departure_time_complete);

        LocalDateTime actualDepartureTime = LocalDateTime.ofEpochSecond(route.getDeparture_time().getValue(), 0, ZoneOffset.of(route.getDeparture_time().getTimezone()));
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(now, actualDepartureTime);
        execution.setVariable("DURATION", duration.toString());
        execution.setVariable("ROUTE",
                objectValue(route)
                        .serializationDataFormat(Variables.SerializationDataFormats.JSON)
                        .create());

    }

}