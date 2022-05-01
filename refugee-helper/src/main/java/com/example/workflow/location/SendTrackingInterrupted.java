package com.example.workflow.location;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.HashMap;

import static com.example.workflow.CheckUserInfo.REFUGEE_APP;

public class SendTrackingInterrupted implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {
        REFUGEE_APP.info("Send Trip Started");

        HashMap map = new HashMap<String, Object>();

        map.put("DESTINATION_REACHED", execution.getVariable("destination_reached"));
        map.put("CURRENT_ADDRESS", execution.getVariable("CURRENT_ADDRESS"));

        if(!((boolean) execution.getVariable("CANCEL_MESSAGE"))){
            execution.getProcessEngineServices().
                    getRuntimeService().
                    createMessageCorrelation("TRACKING_INTERRUPTED").
                    setVariables(map).
                    correlateWithResult();
        }
    }

}