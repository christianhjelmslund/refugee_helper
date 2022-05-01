package com.example.workflow.job;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.HashMap;
import java.util.logging.Logger;

public class SendJobInterest implements JavaDelegate {

    public final static Logger COMPANY = Logger.getLogger("COMPANY");
    public final static Logger REFUGEE_APP_JOB = Logger.getLogger("REFUGEE_APP_JOB");

    public void execute(DelegateExecution execution) throws Exception {
        HashMap map = new HashMap<String, Object>();
        map.put("picked_country", execution.getVariable("picked_country"));
        map.put("process_id", execution.getVariable("process_id"));

        REFUGEE_APP_JOB.info("Interest in jobs sent");

        execution.getProcessEngineServices().
                getRuntimeService().
                createMessageCorrelation("job_interest_sent").
                setVariables(map).
                correlateWithResult();
    }

}