package com.example.workflow;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.HashMap;

public class SendMessage implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {
        HashMap map = new HashMap<String, Object>();
        map.put("name", execution.getVariable("name"));

        execution.getProcessEngineServices().
                getRuntimeService().
                createMessageCorrelation("user_input_data").
                setVariables(map).
                correlateWithResult();
    }

}