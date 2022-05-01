package com.example.workflow.visa;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SendInstanceId implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        execution.getProcessEngineServices().
                getRuntimeService().
                createMessageCorrelation("msg_rcv_instance_id").
                setVariable("backend_instance_id", execution.getProcessInstanceId()).
                processInstanceId((String) execution.getVariable("mobile_visa_instance_id")).
                correlateWithResult();
    }
}
