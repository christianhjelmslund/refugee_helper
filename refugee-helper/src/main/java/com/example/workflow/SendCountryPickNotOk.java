package com.example.workflow;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SendCountryPickNotOk implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {

        execution.getProcessEngineServices().
                getRuntimeService().
                createMessageCorrelation("country_pick_not_ok").
                correlateWithResult();
    }

}