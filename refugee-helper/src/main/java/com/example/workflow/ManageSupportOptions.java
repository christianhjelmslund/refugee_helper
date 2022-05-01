package com.example.workflow;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;

import java.util.HashMap;

import static org.camunda.bpm.engine.variable.Variables.objectValue;

public class ManageSupportOptions implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {

        HashMap<String, String> support_options = new HashMap<>();
        support_options.put("Visa", "Visa");
        support_options.put("Transport", "Transport");

//        if (execution.getVariable("destination_arrived") != null && (boolean) execution.getVariable("destination_arrived")) {
//        }
        support_options.put("Job", "Job");


        execution.setVariable("support_options",
                objectValue(support_options)
                        .serializationDataFormat(Variables.SerializationDataFormats.JSON)
                        .create());

    }

}