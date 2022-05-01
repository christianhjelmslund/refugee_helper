package com.example.workflow.job;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import static com.example.workflow.job.SendJobInterest.FRONTEND_JOB;

public class SendRejectionSystem implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {
        FRONTEND_JOB.info("Send notification on rejection to system");

        execution.getProcessEngineServices().
                getRuntimeService().
                createMessageCorrelation("job_rejection_received").
                correlateWithResult();

    }

}