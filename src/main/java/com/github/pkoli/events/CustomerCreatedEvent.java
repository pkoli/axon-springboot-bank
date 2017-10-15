package com.github.pkoli.events;

/**
 * Created by pkoli on 15/10/17.
 */
public class CustomerCreatedEvent {

    private String customerId;

    public CustomerCreatedEvent(String customerId){
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
