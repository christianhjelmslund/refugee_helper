package com.example.workflow.job;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import static com.example.workflow.job.SendJobInterest.FRONTEND_JOB;

public class AbortCloseJobApplication implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {
        FRONTEND_JOB.info("Close of job application received");

        execution.getProcessEngineServices().
                getRuntimeService().
                createMessageCorrelation("abort_job_application_closed").
                correlateWithResult();

    }

}