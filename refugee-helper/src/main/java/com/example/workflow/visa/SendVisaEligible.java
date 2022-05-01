package com.example.workflow.visa;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SendVisaEligible implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("yoooooo" + execution.getVariable("mobile_visa_instance_id"));
        execution.getProcessEngineServices().
                getRuntimeService().
                createMessageCorrelation("msg_schengen_eligible").
                processInstanceId((String)execution.getVariable("mobile_visa_instance_id")).
                setVariable("visa_eligibility", execution.getVariable("visa_eligibility")).
                setVariable("backend_instance_id", execution.getProcessInstanceId()).
                correlateWithResult();
    }
}
