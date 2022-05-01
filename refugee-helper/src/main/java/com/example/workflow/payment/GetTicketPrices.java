package com.example.workflow.payment;

import com.example.workflow.payment.models.TicketModel;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.delegate.VariableScope;
import org.camunda.bpm.engine.variable.Variables;

import java.util.HashMap;
import java.util.Map;

import static com.example.workflow.CheckUserInfo.REFUGEE_APP;
import static org.camunda.bpm.engine.variable.Variables.objectValue;

public class GetTicketPrices implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {
        //REFUGEE_APP.info("Route Details: " + execution.getVariable("SELECTED_CITY") + ", " + execution.getVariable("CURRENT_ADDRESS") + ", " + execution.getVariable("SELECTED_TRANS_MODE"));

        TicketModel ticketTypeChild = new TicketModel("Children's Ticket", 15.25,"children_ticket");
        TicketModel ticketTypeAdult1 = new TicketModel("Adult Ticket 1st Class", 49.95,"adult_1_ticket");
        TicketModel ticketTypeAdult2 = new TicketModel("Adult Ticket 2nd Class", 30.90,"adult_2_ticket");

        Map<String, Object> ticketMap = new HashMap<String, Object>();
        ticketMap.put(ticketTypeChild.getTicket_type(), ticketTypeChild);
        ticketMap.put(ticketTypeAdult1.getTicket_type(), ticketTypeAdult1);
        ticketMap.put(ticketTypeAdult2.getTicket_type(), ticketTypeAdult2);

        Map<String, String> ticketMapString = new HashMap<String, String>();
        ticketMap.put(ticketTypeChild.getTicket_type(), ticketTypeChild);
        ticketMap.put(ticketTypeAdult1.getTicket_type(), ticketTypeAdult1);
        ticketMap.put(ticketTypeAdult2.getTicket_type(), ticketTypeAdult2);


        execution.setVariable("AVAILABLE_TICKETS_STRINGS",
                objectValue(ticketMapString)
                        .serializationDataFormat(Variables.SerializationDataFormats.JSON)
                        .create());

        execution.setVariable("AVAILABLE_TICKETS_MAP",
                objectValue(ticketMap)
                        .serializationDataFormat(Variables.SerializationDataFormats.JSON)
                        .create());

        execution.getProcessEngineServices().
                getRuntimeService().
                createMessageCorrelation("AVAILABLE_TICKETS").
                setVariables(ticketMap).
                correlateWithResult();
    }

}