package com.example.workflow.payment;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.HashMap;

import static com.example.workflow.CheckUserInfo.REFUGEE_APP;

public class SendPaymentDetails implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {
        HashMap map = new HashMap<String, Object>();
        map.put("CREDIT_CARD", execution.getVariable("CREDIT_NUMBER"));

        REFUGEE_APP.info("Sending Payment Details'");

        execution.getProcessEngineServices().
                getRuntimeService().
                createMessageCorrelation("PAYMENT_DETAILS").
                setVariables(map).
                correlateWithResult();    }

}