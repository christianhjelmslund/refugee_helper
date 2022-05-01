package com.example.workflow.job;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import static com.example.workflow.job.SendJobInterest.FRONTEND_JOB;

public class AcceptJobOffer implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {
        FRONTEND_JOB.info("Accept job offer.");

        execution.getProcessEngineServices().
                getRuntimeService().
                createMessageCorrelation("job_offer_accepted").
                correlateWithResult();
    }

}