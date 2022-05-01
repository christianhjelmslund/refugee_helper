package com.example.workflow.job;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.HashMap;
import java.util.logging.Logger;

import static com.example.workflow.job.SendJobInterest.COMPANY;

public class SendRejection implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {

        COMPANY.info("Sending rejection.");

        execution.getProcessEngineServices().
                getRuntimeService().
                createMessageCorrelation("job_rejection_sent").
                correlateWithResult();
    }

}