package com.example.workflow.visa;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SendCountryFulfilled implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {

        execution.getProcessEngineServices().
                getRuntimeService().
                createMessageCorrelation("msg_country_visa_requirements").
                setVariable("mobile_visa_instance_id", execution.getProcessInstanceId()).
                setVariable("country", execution.getVariable("country")).
                correlateWithResult();
    }
}
