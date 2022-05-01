package com.example.workflow.payment.models;

import java.io.Serializable;
import java.util.Map;

public class TicketOrderModel implements Serializable {
    private Map<String, TicketModel> ticketMap;

    public TicketOrderModel(Map<String, TicketModel> ticketMap) {
        this.ticketMap = ticketMap;
    }

    public Map<String, TicketModel> getTicketMap() {
        return ticketMap;
    }

    public void setTicketMap(Map<String, TicketModel> ticketMap) {
        this.ticketMap = ticketMap;
    }
}
