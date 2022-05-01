package com.example.workflow.visa;

import com.example.workflow.Country;
import com.example.workflow.models.CountryModel;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.*;

import static com.example.workflow.CheckUserInfo.REFUGEE_APP;
import static org.camunda.bpm.engine.variable.Variables.objectValue;

public class    CheckVisaEligibility implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {


        execution.setVariable("visa_eligibility", true);
        execution.setVariable("user_age", 10);

    }
}
