package com.example.workflow.job;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;

import java.util.HashMap;

import java.time.LocalDate;
import java.util.Map;
import java.util.Random;
import java.util.ArrayList;

import static com.example.workflow.job.SendJobInterest.COMPANY_JOB;
import static org.camunda.bpm.engine.variable.Variables.objectValue;

public class InviteInterview implements JavaDelegate {

    String date;
    String time;

    public void execute(DelegateExecution execution) throws Exception {

        ArrayList<InviteInterview> interview_dates = new ArrayList<InviteInterview>();
        HashMap interview_map = new HashMap<String, String>();

        int NUM_DATES = 3;
        int YEAR_START = 2022;
        int YEAR_END = 2022;
        int MONTH_START = 05;
        int MONTH_END = 05;
        int DAY_START = 01;
        int DAY_END = 15;

        for(int i = 0; i < NUM_DATES; i++) {
            Random random = new Random();

            // Random day
            int minDay = (int) LocalDate.of(YEAR_START, MONTH_START, DAY_START).toEpochDay();
            int maxDay = (int) LocalDate.of(YEAR_END, MONTH_END, DAY_END).toEpochDay();
            long randomDay = minDay + random.nextInt(maxDay - minDay);

            String interview_date = LocalDate.ofEpochDay(randomDay).toString();

            // Random time
            int minHour = 9;
            int maxHour = 16;

            int hour_int = random.nextInt((maxHour - minHour) + 1) + minHour;
            String hour = String.valueOf(hour_int);
            if (hour_int < 10){
                hour = "0" + hour;
            }

            String[] min = new String[]{"00","15","30","45"};
            int rnd_min = new Random().nextInt(min.length);
            String minute = min[rnd_min];

            String time = hour + ":" + minute;

            // Add
            InviteInterview interview = new InviteInterview();
            interview.date = interview_date;
            interview.time = time;

            interview_dates.add(interview);

        }

        for(int i = 0; i < interview_dates.size(); i++){
            String key = "interview_suggestion_"+(i+1);
            String value = interview_dates.get(i).date + " - " + interview_dates.get(i).time;
            interview_map.put(key, value);
            COMPANY_JOB.info("Interview date and time suggested: " + key + ", " + value);
        }

        execution.getProcessEngineServices().
                getRuntimeService().
                createMessageCorrelation("interview_timeslot_sent").
                setVariables(interview_map).
                correlateWithResult();
    }

}