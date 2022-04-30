package com.example.workflow;

import com.example.workflow.models.CountriesModel;
import com.example.workflow.models.CountryModel;
import com.example.workflow.services.CountryService;
import com.example.workflow.services.RefugeeService;
import com.example.workflow.transportation.models.CitiesModel;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static com.example.workflow.CheckUserInfo.REFUGEE_APP;
import static org.camunda.bpm.engine.variable.Variables.objectValue;

@Component
public class GetNumberOfRefugees implements JavaDelegate {

    @Autowired
    private RefugeeService refugeeService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        int numberOfRefugees = refugeeService.getNumberOfRefugees((String) execution.getVariable("picked_country"));
        REFUGEE_APP.info("Number of refugees: " + numberOfRefugees);
        execution.setVariable("number_of_refugees", numberOfRefugees);

    }

}
