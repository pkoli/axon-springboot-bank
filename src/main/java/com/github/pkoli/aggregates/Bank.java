package com.github.pkoli.aggregates;

import org.axonframework.commandhandling.model.AggregateRoot;

import java.io.Serializable;

@AggregateRoot
public class Bank implements Serializable {

    private Customer customer;

    public Bank() {
        //Required by JPA
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
