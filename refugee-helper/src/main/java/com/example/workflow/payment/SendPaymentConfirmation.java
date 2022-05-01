package com.example.workflow.payment;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.HashMap;

import static com.example.workflow.CheckUserInfo.REFUGEE_APP;

public class SendPaymentConfirmation implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {
        HashMap map = new HashMap<String, Object>();
        map.put("PAYMENT_CONFIRMATION", execution.getVariable("PAYMENT_CONFIRMATION"));

        REFUGEE_APP.info("Send Payment Confirmation");

        execution.getProcessEngineServices().
                getRuntimeService().
                createMessageCorrelation("PAYMENT_CONFIRMATION").
                setVariables(map).
                correlateWithResult();    }

}