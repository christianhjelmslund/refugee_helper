package com.example.workflow;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import static com.example.workflow.CheckUserInfo.REFUGEE_APP;

public class SendUserWantsToFlee implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {
        REFUGEE_APP.info("User wants to flee");

        execution.getProcessEngineServices().
                getRuntimeService().
                createMessageCorrelation("flee").
                correlateWithResult();
    }

}