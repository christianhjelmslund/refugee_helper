package com.example.workflow;

import com.example.workflow.models.CountriesModel;
import com.example.workflow.models.CountryModel;
import com.example.workflow.services.CountryService;
import com.example.workflow.transportation.models.CitiesModel;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static com.example.workflow.CheckUserInfo.REFUGEE_APP;
import static org.camunda.bpm.engine.variable.Variables.objectValue;

@Component
public class GetCountries implements JavaDelegate {

    @Autowired
    private CountryService countryService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        CountriesModel countries = countryService.getAllCountries();

        Map<String, String> countriesMap = new HashMap<>();
        for(int i = 0; i < countries.getCountries().size(); i++) {
            countriesMap.put(countries.getCountries().get(i).getName(),countries.getCountries().get(i).getName());
        }
        execution.setVariable("countries",
                objectValue(countriesMap)
                        .serializationDataFormat(Variables.SerializationDataFormats.JSON)
                        .create());

        REFUGEE_APP.info("Countries:" + execution.getVariable("countries"));

    }

}
