package com.example.workflow;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.HashMap;

import static com.example.workflow.CheckUserInfo.REFUGEE_APP;

public class SendChosenOption implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {
        HashMap map = new HashMap<String, Object>();
        map.put("option", execution.getVariable("option"));
        map.put("process_id", execution.getProcessInstanceId());

        REFUGEE_APP.info("Sending message [user_input_data]'");

        execution.getProcessEngineServices().
                getRuntimeService().
                createMessageCorrelation("support_chosen").
                setVariables(map).
                correlateWithResult();
    }

}