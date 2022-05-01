package com.example.workflow.payment;

import com.example.workflow.payment.models.TicketModel;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;

import java.util.HashMap;
import java.util.Map;

import static com.example.workflow.CheckUserInfo.REFUGEE_APP;
import static org.camunda.bpm.engine.variable.Variables.objectValue;

public class GetTickets implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {

        REFUGEE_APP.info("Get Tickets Message Send");

        execution.getProcessEngineServices().
                getRuntimeService().
                createMessageCorrelation("GET_TICKETS").
                correlateWithResult();
    }

}