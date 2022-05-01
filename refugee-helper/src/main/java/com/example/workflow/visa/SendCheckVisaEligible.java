package com.example.workflow.visa;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.HashMap;
import java.util.Map;

public class SendCheckVisaEligible implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
//        Map<String, Object> map = new HashMap<>();
//        map.put("mobile_visa_instance_id", execution.getProcessInstanceId());
        execution.getProcessEngineServices().
                getRuntimeService().
                createMessageCorrelation("msg_check_schengen_eligible").
                setVariable("mobile_visa_instance_id", execution.getProcessInstanceId()).
                setVariable("visa_eligibility", execution.getVariable("visa_eligibility")).
                correlateWithResult();
    }
}
