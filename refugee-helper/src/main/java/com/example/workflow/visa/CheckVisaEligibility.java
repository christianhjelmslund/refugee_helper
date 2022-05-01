package com.example.workflow.visa;

import com.example.workflow.models.CountriesModel;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaFormData;
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaFormField;
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaValue;

import java.util.*;

import static com.example.workflow.CheckUserInfo.REFUGEE_APP;
import static org.camunda.bpm.engine.variable.Variables.objectValue;

public class    CheckVisaEligibility implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {


        execution.setVariable("visa_eligibility", true);
        execution.setVariable("user_age", 10);
        execution.setVariable("country_found", false);

        REFUGEE_APP.info("Countries:" + execution.getVariable("countries"));
        REFUGEE_APP.info("Picked country:" + execution.getVariable("picked_country"));
        REFUGEE_APP.info("user_country:" + execution.getVariable("user_country"));


    }
}
