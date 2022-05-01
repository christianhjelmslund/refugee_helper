package com.example.workflow.payment;

import com.example.workflow.payment.exceptions.PaymentException;
import com.example.workflow.payment.services.PaymentService;
import com.example.workflow.transportation.services.CityService;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.example.workflow.CheckUserInfo.REFUGEE_APP;

@Component
public class ProcessTicketPayment implements JavaDelegate {

    @Autowired
    private PaymentService paymentService;

    public void execute(DelegateExecution execution) throws Exception {
        REFUGEE_APP.info("Credit Card Number: " + execution.getVariable("CREDIT_CARD"));

        try {
            execution.setVariable("CREDIT_NUMBER", execution.getVariable("CREDIT_CARD"));
            paymentService.processPayment((String) execution.getVariable("CREDIT_NUMBER"));
            REFUGEE_APP.info("Credit Card Number: " + execution.getVariable("CREDIT_NUMBER"));
            execution.setVariable("PAYMENT_STATE", "SUCCESSFUL");
            REFUGEE_APP.info("Executed Fully");
        } catch (PaymentException e){
            throw new BpmnError("PaymentError");
        }

    }

}