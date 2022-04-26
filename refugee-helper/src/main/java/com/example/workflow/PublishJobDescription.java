package com.example.workflow;

import java.util.HashMap;
import java.util.logging.Logger;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class PublishJobDescription implements JavaDelegate {

    public final static Logger REFUGEE_APP_JOB = Logger.getLogger("REFUGEE_APP_JOB");
    public final static Logger COMPANY = Logger.getLogger("COMPANY");

    public void execute(DelegateExecution execution) throws Exception {
        HashMap map = new HashMap<String, Object>();
        map.put("subject", "The attached job might be of interest to you. Check it out!");
        map.put("job_id", "83949");
        map.put("job_url", "https://www.bmwgroup.jobs/us/en/jobfinder/job-description.57490.html");
        map.put("process_id", execution.getProcessInstanceId());

        COMPANY.info("Publish job description");

        execution.getProcessEngineServices().
                getRuntimeService().
                createMessageCorrelation("job_description_received").
                setVariables(map).
                correlateWithResult();

    }

}