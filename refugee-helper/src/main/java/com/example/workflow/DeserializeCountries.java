package com.example.workflow;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;

import java.util.HashMap;

import static com.example.workflow.CheckUserInfo.REFUGEE_APP;
import static org.camunda.bpm.engine.variable.Variables.objectValue;

public class DeserializeCountries implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {
        REFUGEE_APP.info("List of countries received'" + execution.getVariable("list_of_countries"));

        execution.setVariable("list_of_countries_deserialized",
                objectValue(execution.getVariable("list_of_countries"))
                        .serializationDataFormat(Variables.SerializationDataFormats.JSON)
                        .create());
    }

}