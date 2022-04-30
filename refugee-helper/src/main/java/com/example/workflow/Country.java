package com.example.workflow;

import java.io.Serializable;

public class Country implements Serializable {
    private boolean isEligible = false;
    private final int id;
    private final String name;

    public Country(int id, String name) {
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