package com.example.workflow;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.HashMap;

import static com.example.workflow.CheckUserInfo.REFUGEE_APP;

public class SendAnotherChosenOption implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {
        HashMap map = new HashMap<String, Object>();
        map.put("another_option", execution.getVariable("another_option"));
        map.put("process_id", execution.getProcessInstanceId());

        REFUGEE_APP.info("Sending message [choose_another_support]': " +
                execution.getVariable("another_option")
        );

        execution.getProcessEngineServices().
                getRuntimeService().
                createMessageCorrelation("another_option").
                processInstanceId((String)execution.getVariable("process_id")).
                setVariables(map).
                correlateWithResult();
    }

}