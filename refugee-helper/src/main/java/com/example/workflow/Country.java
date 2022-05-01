package com.example.workflow;

import java.io.Serializable;
import java.util.List;

public class Country implements Serializable {
    private boolean isEligible = false;
    private final String _id;
    private final String name;
    private final List<String> bannedCountries;

    public Country(String id, String name, List<String> bannedCountries) {
        this._id = id;
        this.name = name;
        this.bannedCountries = bannedCountries;
    }

    public boolean isEligible() {
        return isEligible;
    }

    public void setEligible(boolean eligible) {
        isEligible = eligible;
    }

    public String getId() {
        return _id;
    }


    @Override
    public String toString() {
        return "Country{" +
                "isEligible=" + isEligible +
                ", id=" + _id +
                ", name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public List<String> getBannedCountries() {
        return bannedCountries;
    }
}