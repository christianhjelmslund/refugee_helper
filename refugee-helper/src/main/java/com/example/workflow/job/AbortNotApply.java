package com.example.workflow.job;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import static com.example.workflow.job.SendJobInterest.FRONTEND_JOB;

public class AbortNotApply implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {
        FRONTEND_JOB.info("Abort on decision to not apply");

        execution.getProcessEngineServices().
                getRuntimeService().
                createMessageCorrelation("abort_not_apply").
                correlateWithResult();

    }

}