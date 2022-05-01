package com.example.workflow.job;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import static com.example.workflow.job.SendJobInterest.FRONTEND_JOB;

public class AbortJobSupport implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {
        FRONTEND_JOB.info("Abort job support sent");

        execution.getProcessEngineServices().
                getRuntimeService().
                createMessageCorrelation("job_support_aborted").
                correlateWithResult();

    }

}