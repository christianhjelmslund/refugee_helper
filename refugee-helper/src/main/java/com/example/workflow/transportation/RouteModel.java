package com.example.workflow.transportation;

import java.sql.Time;

public class RouteModel {
    private TimeModel arrival_time;
    private TimeModel departure_time;
    private TextValueModel distance;
    private TextValueModel duration;
    private String end_address;
    private GeoLocationModel end_location;
    private String start_address;
    private GeoLocationModel start_location;

    public TimeModel getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(TimeModel arrival_time) {
        this.arrival_time = arrival_time;
    }

    public TimeModel getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(TimeModel departure_time) {
        this.departure_time = departure_time;
    }

    public TextValueModel getDistance() {
        return distance;
    }

    public void setDistance(TextValueModel distance) {
        this.distance = distance;
    }

    public TextValueModel getDuration() {
        return duration;
    }

    public void setDuration(TextValueModel duration) {
        this.duration = duration;
    }

    public String getEnd_address() {
        return end_address;
    }

    public void setEnd_address(String end_address) {
        this.end_address = end_address;
    }

    public GeoLocationModel getEnd_location() {
        return end_location;
    }

    public void setEnd_location(GeoLocationModel end_location) {
        this.end_location = end_location;
    }

    public String getStart_address() {
        return start_address;
    }

    public void setStart_address(String start_address) {
        this.start_address = start_address;
    }

    public GeoLocationModel getStart_location() {
        return start_location;
    }

    public void setStart_location(GeoLocationModel start_location) {
        this.start_location = start_location;
    }
}
