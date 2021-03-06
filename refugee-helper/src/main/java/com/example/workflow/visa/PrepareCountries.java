package com.example.workflow.visa;

import com.example.workflow.Country;
import com.example.workflow.models.CountryModel;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.ArrayList;
import java.util.List;

import static com.example.workflow.CheckUserInfo.REFUGEE_APP;

public class PrepareCountries implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        List<CountryModel> importedCountries = (List<CountryModel>) execution.getVariable("countries");
        List<Country> countries = new ArrayList<>();
        execution.setVariable("country_found", false);

        for (CountryModel country :
                importedCountries) {

            List<String> bannedCountries = new ArrayList<>();
            for (String bannedCountryId :
                    country.getBanned_countries()) {

                for (CountryModel importedCountry2 :
                        importedCountries) {
                    if (importedCountry2.get_id().equals(bannedCountryId)){
                        bannedCountries.add(importedCountry2.getName());
                    }

                }
            }
            countries.add(new Country(country.get_id(), country.getName(), bannedCountries));

        }
        execution.setVariable("countries", countries);

        REFUGEE_APP.info("Countriiiies " + importedCountries);
        REFUGEE_APP.info("Countriiiies " + countries);
        REFUGEE_APP.info("Countriiiies " + countries.size());

        REFUGEE_APP.info("Countries:" + execution.getVariable("countries"));
//        REFUGEE_APP.info("Picked country:" + execution.getVariable("picked_country"));
        REFUGEE_APP.info("user_country:" + execution.getVariable("user_country"));
    }
}
