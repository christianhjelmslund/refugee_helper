package com.example.workflow;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.HashMap;

import static com.example.workflow.CheckUserInfo.REFUGEE_APP;

public class EvaluateCrisis implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {
        REFUGEE_APP.info("User country: " + execution.getVariable("user_country"));
        REFUGEE_APP.info("Crisis Country: " + execution.getVariable("crisis_country"));
        REFUGEE_APP.info("Relevant crisis?: " + execution.getVariable("crisis_country")
                .equals(execution.getVariable("user_country")));

        execution.setVariable("relevant_crisis", execution.getVariable("crisis_country")
                .equals(execution.getVariable("user_country")));

    }

}