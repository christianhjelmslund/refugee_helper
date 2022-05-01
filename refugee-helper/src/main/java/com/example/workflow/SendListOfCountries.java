package com.example.workflow;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.HashMap;

import static com.example.workflow.CheckUserInfo.REFUGEE_APP;

public class SendListOfCountries implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {

        HashMap map = new HashMap<String, Object>();
        map.put("list_of_countries", execution.getVariable("list_of_countries"));
        REFUGEE_APP.info("The list of countries as strings: " + execution.getVariable("countries_as_strings"));
        execution.getProcessEngineServices().
                getRuntimeService().
                createMessageCorrelation("list_of_countries_msg").
                setVariables(map).
                correlateWithResult();
    }

}