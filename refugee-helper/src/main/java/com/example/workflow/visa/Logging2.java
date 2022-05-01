package com.example.workflow.visa;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import static com.example.workflow.CheckUserInfo.REFUGEE_APP;

public class Logging2 implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        REFUGEE_APP.info("Logging2 "+  execution.getVariable("countries"));
    }
}
