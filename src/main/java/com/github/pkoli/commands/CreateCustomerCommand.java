package com.github.pkoli.commands;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

/**
 * Created by pkoli on 15/10/17.
 */
public class CreateCustomerCommand {

    @TargetAggregateIdentifier
    private String customerId;

    private String name;

    private String address;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public CreateCustomerCommand(String name, String address){
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
