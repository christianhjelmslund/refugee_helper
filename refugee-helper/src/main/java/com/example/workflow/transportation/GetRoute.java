package com.example.workflow.transportation;

import com.example.workflow.transportation.models.RouteModel;
import com.example.workflow.transportation.services.RoutingService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.*;
import java.time.format.DateTimeFormatter;

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

        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        if(departure_date == null){
            departure_time_complete = "now";
        }else{
            LocalDateTime localTime = LocalDateTime.parse(departure_date + "T" + departure_time + ":00");
            departure_time_complete = localTime.format(myFormatObj);
        }
        execution.setVariable("DEPARTURE", departure_time_complete);
        REFUGEE_APP.info("Destination City: " + destination_city + " ," + "Current Address: " + current_address + " ," +"Mode: " + mode + " ," +"Departure Time: " + departure_time_complete);
        RouteModel route = routingService.getRoute(destination_city, current_address, mode, departure_time_complete);

        Instant instant = Instant.now();
        ZoneId zone = ZoneId.of(route.getDeparture_time().getTime_zone());

        //TODO: Change Back to Actual Departure Time this is only for testing purposes
        //LocalDateTime actualDepartureTime = LocalDateTime.ofEpochSecond(route.getDeparture_time().getValue(), 0, zone.getRules().getOffset(instant));
        //TODO: Remove after testing
        LocalDateTime actualDepartureTime = LocalDateTime.parse(departure_time_complete, myFormatObj);

        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(now, actualDepartureTime);
        execution.setVariable("DURATION", duration.toString());
        execution.setVariable("ROUTE",
                objectValue(route)
                        .serializationDataFormat(Variables.SerializationDataFormats.JSON)
                        .create());

    }

}