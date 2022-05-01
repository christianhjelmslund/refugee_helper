package com.example.workflow.job;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.Random;

import static com.example.workflow.job.SendJobInterest.COMPANY;

public class DecideCandidate implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {
        // Randomly decide if user is invited for interview
        Random random = new Random();
        String decision_user;

        int random_int = random.nextInt(10);

        if (random_int > 8){
            decision_user = "accept";
        } else {
            decision_user = "reject";
        }

        COMPANY.info("Decision to on user's application: " + decision_user);
        execution.setVariable("decision_user", decision_user);
    }

}