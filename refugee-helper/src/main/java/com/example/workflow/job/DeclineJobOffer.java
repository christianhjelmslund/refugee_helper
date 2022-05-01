package com.example.workflow.job;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import static com.example.workflow.job.SendJobInterest.REFUGEE_APP_JOB;

public class DeclineJobOffer implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {

        REFUGEE_APP_JOB.info("Decline job offer.");

        execution.getProcessEngineServices().
                getRuntimeService().
                createMessageCorrelation("job_offer_declined").
                correlateWithResult();
    }

}