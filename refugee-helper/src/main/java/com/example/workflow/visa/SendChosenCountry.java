package com.example.workflow.visa;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SendChosenCountry implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        execution.getProcessEngineServices().
                getRuntimeService().
                createMessageCorrelation("msg_chosen_country").
                setVariable("visa_country_ok", execution.getVariable("visa_country_ok")).
                processInstanceId((String) execution.getVariable("backend_instance_id")).
                correlateWithResult();

    }
}
