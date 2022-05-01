package com.example.workflow.location;

import com.example.workflow.location.models.AddressModel;
import com.example.workflow.location.services.AddressService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

import static com.example.workflow.CheckUserInfo.REFUGEE_APP;

@Component
public class GetCurrentAddress implements JavaDelegate {

    @Autowired
    private AddressService addressService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        float lat = (float) execution.getVariable("lat");
        float lng = (float) execution.getVariable("lng");
        String address = addressService.getAddress(lat, lng);
        REFUGEE_APP.info("Address received: " + address);

        if(!((boolean) execution.getVariable("DESTINATION_REACHED"))){
            REFUGEE_APP.info("Address Set");
            execution.setVariable("CURRENT_ADDRESS", address);
        }else{
            REFUGEE_APP.info("Address Not Set");
            execution.setVariable("CANCEL_MESSAGE", true);
        }

    }
}