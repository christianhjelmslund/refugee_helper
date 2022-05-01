package com.example.workflow.transportation;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.HashMap;

import static com.example.workflow.CheckUserInfo.REFUGEE_APP;

public class SendRoute implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {
        REFUGEE_APP.info("Route Details: " + execution.getVariable("ROUTE"));

        HashMap map = new HashMap<String, Object>();

        map.put("ROUTE", execution.getVariable("ROUTE"));

        execution.getProcessEngineServices().
                getRuntimeService().
                createMessageCorrelation("CHOOSEN_ROUTE").
                setVariables(map).
                correlateWithResult();
    }

}