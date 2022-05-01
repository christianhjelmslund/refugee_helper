package com.example.workflow.job;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import static com.example.workflow.job.SendJobInterest.FRONTEND_JOB;

public class AbortProvidedSupport implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {
        FRONTEND_JOB.info("Abort on provided job support");

        execution.getProcessEngineServices().
                getRuntimeService().
                createMessageCorrelation("abort_provided_support").
                correlateWithResult();

    }

}