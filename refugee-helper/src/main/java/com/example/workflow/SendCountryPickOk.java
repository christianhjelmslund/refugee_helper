package com.example.workflow;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SendCountryPickOk implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {

        execution.getProcessEngineServices().
                getRuntimeService().
                createMessageCorrelation("country_pick_ok").
                correlateWithResult();
    }

}