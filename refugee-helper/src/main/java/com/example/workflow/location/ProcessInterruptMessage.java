package com.example.workflow.location;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;

import java.util.HashMap;

import static com.example.workflow.CheckUserInfo.REFUGEE_APP;
import static org.camunda.bpm.engine.variable.Variables.objectValue;

public class ProcessInterruptMessage implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {
        REFUGEE_APP.info("Interrupt Message Processed");

        HashMap map = new HashMap<String, String>();

        map.put("001", execution.getVariable("CURRENT_ADDRESS"));

        execution.setVariable("AVAILABLE_CITIES",
                objectValue(map)
                        .serializationDataFormat(Variables.SerializationDataFormats.JSON)
                        .create());
    }

}