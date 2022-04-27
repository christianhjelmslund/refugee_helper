package com.example.workflow;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class EvaluateCountry implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Country country = (Country) execution.getVariable("country");

        if (country.getId()  != 2) {
            country.setEligible(true);
        }
    }
}
