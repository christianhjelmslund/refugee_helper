package com.example.workflow.job;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.logging.Logger;

public class SendJobInterest implements JavaDelegate {

    public final static Logger COMPANY_JOB = Logger.getLogger("COMPANY");
    public final static Logger FRONTEND_JOB = Logger.getLogger("FRONTEND_JOB");
    public final static Logger BACKEND_JOB = Logger.getLogger("BACKEND_JOB");

    public void execute(DelegateExecution execution) throws Exception {
        FRONTEND_JOB.info("Job Frontend started");

        execution.getProcessEngineServices().
                getRuntimeService().
                createMessageCorrelation("job_interest_sent").
                correlateWithResult();
    }

}