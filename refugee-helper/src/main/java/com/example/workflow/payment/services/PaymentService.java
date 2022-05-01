package com.example.workflow.payment.services;

import com.example.workflow.payment.exceptions.PaymentException;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public String processPayment(String creditCardNumber) throws PaymentException {
        if (creditCardNumber.length()!=16){
            throw new PaymentException("Invalid Credit Card Number");
        }else{
            return "Payment Successful";
        }
    }
}
