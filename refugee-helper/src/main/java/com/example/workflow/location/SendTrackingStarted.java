package com.example.workflow.location;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.HashMap;

import static com.example.workflow.CheckUserInfo.REFUGEE_APP;

public class SendTrackingStarted implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {
        REFUGEE_APP.info("Send Location Tracking Started");

        HashMap map = new HashMap<String, Object>();

        map.put("TRACKING_STATUS", execution.getVariable("Started"));

        execution.getProcessEngineServices().
                getRuntimeService().
                createMessageCorrelation("LOCATION_TRACKING_START").
                setVariables(map).
                correlateWithResult();
    }

}