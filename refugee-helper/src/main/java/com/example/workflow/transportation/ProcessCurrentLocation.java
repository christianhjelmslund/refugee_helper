package com.example.workflow.transportation;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;

import java.util.HashMap;
import java.util.Map;

import static org.camunda.bpm.engine.variable.Variables.objectValue;

public class ProcessCurrentLocation implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {

        Map<String, String> citiesMap = (HashMap) execution.getVariable("AVAILABLE_CITIES_MESSAGE");


        execution.setVariable("AVAILABLE_CITIES",
                objectValue(citiesMap)
                        .serializationDataFormat(Variables.SerializationDataFormats.JSON)
                        .create());
    }
}
