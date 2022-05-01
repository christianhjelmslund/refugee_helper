package com.example.workflow.transportation;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.HashMap;

import static com.example.workflow.CheckUserInfo.REFUGEE_APP;

public class SendRouteDetails implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {
        REFUGEE_APP.info("Route Details: " + execution.getVariable("SELECTED_CITY") + ", " + execution.getVariable("CURRENT_ADDRESS") + ", " + execution.getVariable("SELECTED_TRANS_MODE"));

        HashMap map = new HashMap<String, Object>();

        map.put("SELECTED_CITY", execution.getVariable("SELECTED_CITY"));
        map.put("CURRENT_ADDRESS", execution.getVariable("CURRENT_ADDRESS"));
        map.put("SELECTED_TRANS_MODE", execution.getVariable("SELECTED_TRANS_MODE"));
        map.put("SELECTED_TRANS_MODE", execution.getVariable("DATE"));
        map.put("SELECTED_TRANS_MODE", execution.getVariable("TIME"));

        execution.getProcessEngineServices().
                getRuntimeService().
                createMessageCorrelation("ROUTE_DETAILS").
                setVariables(map).
                correlateWithResult();
    }

}