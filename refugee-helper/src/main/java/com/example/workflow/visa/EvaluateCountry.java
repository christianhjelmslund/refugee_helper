package com.example.workflow.visa;

import com.example.workflow.Country;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class EvaluateCountry implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Country country = (Country) execution.getVariable("country");
        String userCountry = (String) execution.getVariable("user_country");

        System.out.println("banned countries " + country.getBannedCountries());
        System.out.println("userCountry " + userCountry);
        boolean eligible = !country.getBannedCountries().contains(userCountry);
        System.out.println("setting eligible " + country.getName() + eligible);
        country.setEligible(eligible);
    }
}
