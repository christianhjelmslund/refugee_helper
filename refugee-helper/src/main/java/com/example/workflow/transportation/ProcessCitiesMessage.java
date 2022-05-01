package com.example.workflow.transportation;

import com.example.workflow.payment.models.TicketModel;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;

import java.util.HashMap;
import java.util.Map;

import static org.camunda.bpm.engine.variable.Variables.objectValue;

public class ProcessCitiesMessage implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {

        execution.setVariable("AVAILABLE_CITIES",
                objectValue(execution.getVariable("LIST_OF_CITIES"))
                        .serializationDataFormat(Variables.SerializationDataFormats.JSON)
                        .create());

    }

}