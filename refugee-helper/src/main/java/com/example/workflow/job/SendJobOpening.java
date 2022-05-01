package com.example.workflow.job;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.HashMap;

import static com.example.workflow.job.SendJobInterest.COMPANY_JOB;

public class SendJobOpening implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {
        HashMap SendJobOpening_map = new HashMap<String, Object>();
        SendJobOpening_map.put("job_id", "57490");
        SendJobOpening_map.put("job_url", "https://www.bmwgroup.jobs/us/en/jobfinder/job-description.57490.html");

        COMPANY_JOB.info("Sending job opening.");

        execution.setVariable("app_user_applied", "false");

        execution.getProcessEngineServices().
                getRuntimeService().
                createMessageCorrelation("job_opening_sent").
                setVariables(SendJobOpening_map).
                correlateWithResult();
    }

}