package com.example.workflow;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import static com.example.workflow.CheckUserInfo.SYSTEM;

public class SendRelevantCrisisDetected implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {
        SYSTEM.info("Relevant crisis detected [relevant_crisis_detected]'");


        execution.getProcessEngineServices().
                getRuntimeService().
                createMessageCorrelation("relevant_crisis_detected").
                processInstanceId((String)execution.getVariable("process_id_frontend")).
                correlateWithResult();
    }

}