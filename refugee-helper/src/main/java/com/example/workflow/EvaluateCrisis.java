package com.example.workflow;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.HashMap;

import static com.example.workflow.CheckUserInfo.REFUGEE_APP;

public class EvaluateCrisis implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {
//        HashMap map = new HashMap<String, Object>();
//        map.put("country", execution.getVariable("country"));

        REFUGEE_APP.info("Sending message [user_input_data_ok]'" + execution.getVariable("country"));

        execution.setVariable("country_ok", !((String) execution.getVariable("country")).isEmpty());


//        execution.getProcessEngineServices().
//                getRuntimeService().
//                createMessageCorrelation("user_input_data").
//                setVariables(map).
//                correlateWithResult();
    }

}