package com.example.workflow.visa;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import static com.example.workflow.CheckUserInfo.REFUGEE_APP;
public class SetCountryFound implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        REFUGEE_APP.info("Reaaached final flow yiir");
        execution.setVariable("country_found", true);
        execution.setVariable("found_country", execution.getVariable("country"));
    }
}
