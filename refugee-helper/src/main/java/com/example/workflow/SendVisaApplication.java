package com.example.workflow;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import static com.example.workflow.CheckUserInfo.REFUGEE_APP;

public class SendVisaApplication implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        REFUGEE_APP.info("Country found! " + execution.getVariable("found_country"));
    }
}
