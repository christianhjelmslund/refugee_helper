package com.example.workflow.job;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.HashMap;
import java.util.logging.Logger;

import static com.example.workflow.job.SendJobInterest.COMPANY;

public class SendSelectedInterview implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {
        HashMap selected_interview_map = new HashMap<String, Object>();
        selected_interview_map.put("selected_interview", execution.getVariable("SELECTED_INTERVIEW"));
        selected_interview_map.put("process_id", execution.getVariable("process_id"));

        COMPANY.info("Selected time slot sent: " + selected_interview_map.get(0));

        execution.getProcessEngineServices().
                getRuntimeService().
                createMessageCorrelation("selected_interview_sent").
                setVariables(selected_interview_map).
                correlateWithResult();
    }

}