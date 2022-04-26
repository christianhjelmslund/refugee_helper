package com.example.workflow;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import static com.example.workflow.CheckUserInfo.REFUGEE_APP;

public class ObtainVisaRequirements implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        REFUGEE_APP.info("Executing :) ");
        REFUGEE_APP.info("Executing :) " + execution.getVariable("country"));


    }
}
