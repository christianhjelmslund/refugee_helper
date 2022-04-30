package com.example.workflow.visa;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SendCheckVisaEligible implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        execution.getProcessEngineServices().
                getRuntimeService().
                createMessageCorrelation("msg_check_schengen_eligible").
                correlateWithResult();
    }
}
