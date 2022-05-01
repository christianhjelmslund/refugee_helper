package com.example.workflow.job;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;

import java.util.HashMap;
import java.util.Map;

import static com.example.workflow.job.SendJobInterest.FRONTEND_JOB;
import static org.camunda.bpm.engine.variable.Variables.objectValue;

public class RetrieveSuggestedTimes implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {

        Map<String, String> retrieved_interviews_map = new HashMap<String, String>();

        retrieved_interviews_map.put("interview_suggestion_1", execution.getVariable("interview_suggestion_1").toString());
        retrieved_interviews_map.put("interview_suggestion_2", execution.getVariable("interview_suggestion_2").toString());
        retrieved_interviews_map.put("interview_suggestion_3", execution.getVariable("interview_suggestion_3").toString());

        FRONTEND_JOB.info("Interview suggestions received");

        execution.setVariable("SELECTION_INTERVIEWS",
                objectValue(retrieved_interviews_map)
                        .serializationDataFormat(Variables.SerializationDataFormats.JSON)
                        .create());
    }

}