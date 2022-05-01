package com.example.workflow.payment;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.HashMap;

import static com.example.workflow.CheckUserInfo.REFUGEE_APP;

public class SendPaymentError implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {
        HashMap map = new HashMap<String, Object>();
        map.put("PAYMENT_ERROR", execution.getVariable("PAYMENT_ERROR"));

        REFUGEE_APP.info("Send Payment Error'");

        execution.getProcessEngineServices().
                getRuntimeService().
                createMessageCorrelation("PAYMENT_ERROR_MSG_1").
                setVariables(map).
                correlateWithResult();    }

}