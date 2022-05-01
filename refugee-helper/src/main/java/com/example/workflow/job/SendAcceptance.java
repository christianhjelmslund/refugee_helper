package com.example.workflow.job;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.HashMap;

import static com.example.workflow.job.SendJobInterest.COMPANY;

public class SendAcceptance implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {

        COMPANY.info("Sending acceptance.");

        execution.getProcessEngineServices().
                getRuntimeService().
                createMessageCorrelation("job_acceptance_sent").
                correlateWithResult();
    }

}