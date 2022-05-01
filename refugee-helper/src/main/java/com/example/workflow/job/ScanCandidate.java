package com.example.workflow.job;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import java.util.Random;

import java.util.HashMap;

import static com.example.workflow.job.SendJobInterest.COMPANY_JOB;

public class ScanCandidate implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {
        // Randomly decide if user is invited for interview
        Random random = new Random();
        String invite_user;

        int random_int = random.nextInt(10);

        if (random_int > 2){
            invite_user = "true";
        } else {
            invite_user = "false";
        }

        COMPANY_JOB.info("Decision to invite user for interview: " + invite_user);
        execution.setVariable("invite_user", invite_user);
    }

}