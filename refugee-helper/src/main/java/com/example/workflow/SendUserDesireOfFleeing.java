package com.example.workflow;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.HashMap;

import static com.example.workflow.CheckUserInfo.REFUGEE_APP;

public class SendUserDesireOfFleeing implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {
        REFUGEE_APP.info("User wants to flee");

        HashMap map = new HashMap<String, Object>();
        map.put("desire_to_escape_msg", execution.getVariable("desire_to_escape"));

        execution.getProcessEngineServices().
                getRuntimeService().
                createMessageCorrelation("desire_to_escape_msg").
                setVariables(map).
                processInstanceId((String)execution.getVariable("process_id")).
                correlateWithResult();
    }

}