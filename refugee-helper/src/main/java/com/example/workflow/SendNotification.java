package com.example.workflow;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.HashMap;

import static com.example.workflow.CheckUserInfo.REFUGEE_APP;

public class SendNotification implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {
        HashMap map = new HashMap<String, Object>();
        map.put("country", execution.getVariable("country"));

        REFUGEE_APP.info("Sending notification '");

        execution.getProcessEngineServices().
                getRuntimeService().
                createMessageCorrelation("user_notification_data").
                setVariables(map).
                correlateWithResult();    }

}