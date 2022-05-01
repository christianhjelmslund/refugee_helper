package com.example.workflow.job;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import static com.example.workflow.job.SendJobInterest.COMPANY_JOB;

import java.util.HashMap;

public class FilterJobs implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {

        HashMap job_selection_map = new HashMap<String, String>();
        job_selection_map.put("job_selection_1", "BMW is da shit");
        job_selection_map.put("job_selection_2", "BMW 2");
        job_selection_map.put("job_selection_3", "BMW 3");
        job_selection_map.put("job_selection_4", "BMW 4");
        job_selection_map.put("job_selection_5", "BMW 5");

        COMPANY_JOB.info("Jobs based on destination country filtered");

        execution.getProcessEngineServices().
                getRuntimeService().
                createMessageCorrelation("jobs_selection_sent").
                setVariables(job_selection_map).
                correlateWithResult();


    }

}