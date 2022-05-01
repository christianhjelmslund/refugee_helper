package com.example.workflow;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.HashMap;

import static com.example.workflow.CheckUserInfo.REFUGEE_APP;

public class PickedCountry implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {
        REFUGEE_APP.info("Picked countries: " + execution.getVariable("picked_country"));

        HashMap map = new HashMap<String, Object>();

        map.put("picked_country", execution.getVariable("picked_country"));

        execution.getProcessEngineServices().
                getRuntimeService().
                createMessageCorrelation("picked_country").
                processInstanceId((String)execution.getVariable("process_id_backend")).
                setVariables(map).
                correlateWithResult();
    }

}