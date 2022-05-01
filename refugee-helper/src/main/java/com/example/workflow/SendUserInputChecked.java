package com.example.workflow;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.HashMap;

import static com.example.workflow.CheckUserInfo.REFUGEE_APP;

public class SendUserInputChecked implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {
        HashMap map = new HashMap<String, Object>();
        map.put("user_input_data_ok", execution.getVariable("user_input_ok"));
        map.put("process_id_backend", execution.getProcessInstanceId());

        REFUGEE_APP.info("Sending message [user_input_data_ok]'");

        execution.getProcessEngineServices().
                getRuntimeService().
                createMessageCorrelation("user_input_data_ok").
                processInstanceId((String)execution.getVariable("process_id_frontend")).
                setVariables(map).
                correlateWithResult();
    }

}