package com.example.workflow;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.HashMap;

import static com.example.workflow.CheckUserInfo.REFUGEE_APP;

public class SendCountryPickOk implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {

        REFUGEE_APP.info("Countriesdd :" + execution.getVariable("countries"));

        HashMap map = new HashMap<String, Object>();
        map.put("countries_objects", execution.getVariable("countries_objects"));

        execution.getProcessEngineServices().
                getRuntimeService().
                createMessageCorrelation("country_pick_ok").
                setVariables(map).
                correlateWithResult();
    }

}