package com.example.workflow.visa;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SendExpiryDateEval implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        execution.getProcessEngineServices().
                getRuntimeService().
                createMessageCorrelation("msg_expiry_date_evaluation").
                processInstanceId((String)execution.getVariable("mobile_visa_instance_id")).
                setVariable("visa_expired", execution.getVariable("visa_expired")).
                correlateWithResult();
        execution.setVariable("country_found", false);

    }
}
