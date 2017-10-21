package com.github.pkoli.events;

public class DeletedCustomerEvent {

    private String customerId;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public DeletedCustomerEvent(String customerId) {
        this.customerId = customerId;
    }
}
