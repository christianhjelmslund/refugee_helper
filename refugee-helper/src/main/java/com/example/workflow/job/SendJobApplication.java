package com.example.workflow.job;

import java.util.HashMap;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import static com.example.workflow.job.SendJobInterest.REFUGEE_APP_JOB;

public class SendJobApplication implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {
        HashMap map = new HashMap<String, Object>();
        map.put("app_user_applied", "true");

        REFUGEE_APP_JOB.info("Application sent");

        execution.getProcessEngineServices().
                getRuntimeService().
                createMessageCorrelation("job_application_sent").
                setVariables(map).
                correlateWithResult();

    }

}