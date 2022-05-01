package com.example.workflow;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.HashMap;

import static com.example.workflow.CheckUserInfo.REFUGEE_APP;

public class SendCountryPickOk implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {

        HashMap map = new HashMap<String, Object>();
        map.put("countries", execution.getVariable("countries"));

        System.out.println("Country pick ok");
        execution.getProcessEngineServices().
                getRuntimeService().
                createMessageCorrelation("country_pick_ok").
                processInstanceId((String)execution.getVariable("process_id")).
                setVariables(map).
                correlateWithResult();
    }

}