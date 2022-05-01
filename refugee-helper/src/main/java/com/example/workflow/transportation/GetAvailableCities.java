package com.example.workflow.transportation;

import com.example.workflow.transportation.models.CitiesModel;
import com.example.workflow.transportation.services.CityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static com.example.workflow.CheckUserInfo.REFUGEE_APP;
import static org.camunda.bpm.engine.variable.Variables.objectValue;

@Component
public class GetAvailableCities implements JavaDelegate {

    @Autowired
    private CityService cityService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Object destination_country = execution.getVariable("destination_country");
        REFUGEE_APP.info("Get Cities for Country:" + destination_country);

        CitiesModel cities = cityService.findAllCitiesByCountryName(destination_country.toString());

        REFUGEE_APP.info("Available Cities for Country:" + destination_country + " ," + cities.getCities().get(0).getName());
        Map<String, Object> citiesMap = new HashMap<String, Object>();
        for(int i = 0; i < cities.getCities().size(); i++) {
            citiesMap.put(cities.getCities().get(i).getName(),cities.getCities().get(i).getName());
        }

        execution.getProcessEngineServices().
                getRuntimeService().
                createMessageCorrelation("AVAILABLE_CITIES_MESSAGE").
                setVariables(citiesMap).
                correlateWithResult();

    }

}
