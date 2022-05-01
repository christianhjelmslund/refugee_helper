package com.example.workflow.job;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.HashMap;
import java.util.Random;

import static com.example.workflow.job.SendJobInterest.COMPANY_JOB;

public class MakeDecision implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {
        // Randomly decide if user is invited for interview
        int MAX_RATING = 10;

        String provided_rating = execution.getVariable("INTERVIEW_RATING").toString();

        COMPANY_JOB.info("Selected rating: " + provided_rating);

        int interview_rating = MAX_RATING-Integer.valueOf(provided_rating);
        Random random = new Random();
        String decision_user;

        int random_int = random.nextInt(MAX_RATING);

        if (random_int > interview_rating){
            decision_user = "accept";
        } else {
            decision_user = "reject";
        }

        COMPANY_JOB.info("Decision to on user's application: " + decision_user);
        execution.setVariable("decision_user", decision_user);
    }

}