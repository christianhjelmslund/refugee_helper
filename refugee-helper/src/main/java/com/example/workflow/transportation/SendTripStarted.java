package com.example.workflow.transportation;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.HashMap;

import static com.example.workflow.CheckUserInfo.REFUGEE_APP;

public class SendTripStarted implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {
        REFUGEE_APP.info("Send Trip Started");

        HashMap map = new HashMap<String, Object>();

        map.put("TRIP_STATUS", execution.getVariable("Started"));

        execution.getProcessEngineServices().
                getRuntimeService().
                createMessageCorrelation("TRIP_STARTED").
                setVariables(map).
                correlateWithResult();
    }

}