package com.example.workflow;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.example.workflow.CheckUserInfo.REFUGEE_APP;
class Country implements Serializable {
    private boolean isEligible = false;
    private final int id;
    private final String name;

    Country(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public boolean isEligible() {
        return isEligible;
    }

    public void setEligible(boolean eligible) {
        isEligible = eligible;
    }

    public int getId() {
        return id;
    }


    @Override
    public String toString() {
        return "Country{" +
                "isEligible=" + isEligible +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }
}
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
