package com.example.workflow.payment;

import com.example.workflow.payment.exceptions.PaymentException;
import com.example.workflow.payment.models.TicketModel;
import com.example.workflow.payment.services.PaymentService;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;

import java.util.HashMap;
import java.util.Map;

import static com.example.workflow.CheckUserInfo.REFUGEE_APP;
import static org.camunda.bpm.engine.variable.Variables.objectValue;

public class ProcessTicketPrices implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {

        Map<String, String> pricesMap = new HashMap<String, String>();
        TicketModel children_ticket = (TicketModel) execution.getVariable("children_ticket");
        TicketModel adult_1_ticket = (TicketModel) execution.getVariable("adult_1_ticket");
        TicketModel adult_2_ticket = (TicketModel) execution.getVariable("adult_2_ticket");
        pricesMap.put("children_ticket", children_ticket.getTicket_name());
        pricesMap.put("adult_1_ticket", adult_1_ticket.getTicket_name());
        pricesMap.put("adult_2_ticket", adult_2_ticket.getTicket_name());

        execution.setVariable("AVAILABLE_TICKETS_STRINGS",
                objectValue(pricesMap)
                        .serializationDataFormat(Variables.SerializationDataFormats.JSON)
                        .create());
    }

}