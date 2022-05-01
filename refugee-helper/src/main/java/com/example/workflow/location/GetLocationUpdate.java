package com.example.workflow.location;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.HashMap;

import static com.example.workflow.CheckUserInfo.REFUGEE_APP;

public class GetLocationUpdate implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {
        REFUGEE_APP.info("Received new Location");
    }

}