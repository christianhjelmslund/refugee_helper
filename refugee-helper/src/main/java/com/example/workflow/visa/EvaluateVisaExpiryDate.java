package com.example.workflow.visa;

import com.example.workflow.Country;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.example.workflow.CheckUserInfo.REFUGEE_APP;
public class EvaluateVisaExpiryDate implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {
        Date date = (Date) execution.getVariable("date_of_arrival");
        execution.setVariable("default_date", date);

        REFUGEE_APP.info("Date of arrival" +  date.toString());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();

        ArrayList<Country> countries = new ArrayList<>();
        countries.add(new Country(0, "Spain"));
        countries.add(new Country( 1, "Germany"));
        countries.add(new Country( 2, "Portugal"));
        countries.add(new Country( 3, "France"));
        countries.add(new Country( 4, "Denmark"));



        execution.setVariable("countries", countries);

        c.setTime(date);
        c.add(Calendar.DATE, 90);
        Date expiryDate = c.getTime();
        Date now = new Date();
        REFUGEE_APP.info("expiry date: " + expiryDate + " " + expiryDate.before(now));

        execution.setVariable("visa_expired", expiryDate.before(now));


    }
}
