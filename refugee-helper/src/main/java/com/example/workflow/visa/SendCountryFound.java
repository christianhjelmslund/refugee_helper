package com.example.workflow.visa;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SendCountryFound implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        execution.getProcessEngineServices().
                getRuntimeService().
                createMessageCorrelation("msg_country_found").
                processInstanceId((String) execution.getVariable("mobile_visa_instance_id")).
                setVariable("country", execution.getVariable("found_country")).
                correlateWithResult();

    }
}
