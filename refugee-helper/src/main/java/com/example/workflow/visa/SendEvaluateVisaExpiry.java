package com.example.workflow.visa;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SendEvaluateVisaExpiry implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        execution.getProcessEngineServices().
                getRuntimeService().
                createMessageCorrelation("msg_evaluate_expiry_date").
                setVariable("date_of_arrival", execution.getVariable("date_of_arrival")).
                correlateWithResult();

    }
}
