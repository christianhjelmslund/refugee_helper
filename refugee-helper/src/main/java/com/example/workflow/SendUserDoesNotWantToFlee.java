package com.example.workflow;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import static com.example.workflow.CheckUserInfo.REFUGEE_APP;

public class SendUserDoesNotWantToFlee implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {
        REFUGEE_APP.info("User does not want to flee");

        execution.getProcessEngineServices().
                getRuntimeService().
                createMessageCorrelation("not_flee").
                processInstanceId((String)execution.getVariable("process_id")).
                correlateWithResult();
    }

}