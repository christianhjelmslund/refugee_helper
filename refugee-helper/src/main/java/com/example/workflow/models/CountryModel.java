package com.example.workflow.models;

import java.util.ArrayList;

public class CountryModel {
    private String _id;
    private String name;
    private int capacity;
    private ArrayList<String> city_ids;
    private boolean visa_available;
    private String visa_requirements;
    private ArrayList<String> banned_countries;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public ArrayList<String> getCity_ids() {
        return city_ids;
    }

    public void setCity_ids(ArrayList<String> city_ids) {
        this.city_ids = city_ids;
    }

    public boolean isVisa_available() {
        return visa_available;
    }

    public void setVisa_available(boolean visa_available) {
        this.visa_available = visa_available;
    }

    public String getVisa_requirements() {
        return visa_requirements;
    }

    public void setVisa_requirements(String visa_requirements) {
        this.visa_requirements = visa_requirements;
    }

    public ArrayList<String> getBanned_countries() {
        return banned_countries;
    }

    public void setBanned_countries(ArrayList<String> banned_countries) {
        this.banned_countries = banned_countries;
    }
}
