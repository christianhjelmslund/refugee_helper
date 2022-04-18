package com.example.workflow;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import static com.example.workflow.CheckUserInfo.SYSTEM;

public class CrisisDetected implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {
        SYSTEM.info("Crisis detected");
    }

}