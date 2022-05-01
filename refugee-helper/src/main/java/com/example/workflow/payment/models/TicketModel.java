package com.example.workflow.payment.models;

import java.io.Serializable;

public class TicketModel implements Serializable {
    private String ticket_name;
    private double ticket_price;
    private String ticket_type;

    public TicketModel(String ticket_name, double ticket_price, String ticket_type) {
        this.ticket_name = ticket_name;
        this.ticket_price = ticket_price;
        this.ticket_type = ticket_type;
    }

    public String getTicket_name() {
        return ticket_name;
    }

    public void setTicket_name(String ticket_name) {
        this.ticket_name = ticket_name;
    }

    public double getTicket_price() {
        return ticket_price;
    }

    public void setTicket_price(double ticket_price) {
        this.ticket_price = ticket_price;
    }

    public String getTicket_type() {
        return ticket_type;
    }

    public void setTicket_type(String ticket_type) {
        this.ticket_type = ticket_type;
    }
}
