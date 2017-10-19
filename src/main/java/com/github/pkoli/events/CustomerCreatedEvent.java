package com.github.pkoli.events;

import com.github.pkoli.aggregates.Customer;

/**
 * Created by pkoli on 15/10/17.
 */
public class CustomerCreatedEvent {

    private Customer customer;

    public CustomerCreatedEvent(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
