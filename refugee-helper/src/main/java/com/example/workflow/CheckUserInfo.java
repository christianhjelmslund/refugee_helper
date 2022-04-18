package com.example.workflow;

import java.util.logging.Logger;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class CheckUserInfo implements JavaDelegate {

    public final static Logger SYSTEM = Logger.getLogger("SYSTEM");
    public final static Logger REFUGEE_APP = Logger.getLogger("REFUGEE_APP");

    public void execute(DelegateExecution execution) throws Exception {
        SYSTEM.info("Processing request by '" + execution.getVariable("name") + "'...");

        execution.setVariable("user_input_ok", !((String) execution.getVariable("name")).isEmpty());

    }

}