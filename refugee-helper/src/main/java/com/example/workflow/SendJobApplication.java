package com.example.workflow;

import java.util.HashMap;
import java.util.logging.Logger;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import static com.example.workflow.PublishJobDescription.REFUGEE_APP_JOB;

public class SendJobApplication implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {
        HashMap map = new HashMap<String, Object>();
        map.put("subject", "Application");

        REFUGEE_APP_JOB.info("Application sent");

        execution.getProcessEngineServices().
                getRuntimeService().
                createMessageCorrelation("job_application_received").
                setVariables(map).
                correlateWithResult();

    }

}