package com.example.workflow.job;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import static com.example.workflow.job.SendJobInterest.COMPANY_JOB;

public class SendAcceptance implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {

        COMPANY_JOB.info("Sending acceptance.");

        execution.getProcessEngineServices().
                getRuntimeService().
                createMessageCorrelation("job_acceptance_sent").
                correlateWithResult();
    }

}