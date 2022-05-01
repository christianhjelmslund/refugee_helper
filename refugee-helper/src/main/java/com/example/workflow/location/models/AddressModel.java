package com.example.workflow.location.models;

import java.io.Serializable;

public class AddressModel implements Serializable {
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
