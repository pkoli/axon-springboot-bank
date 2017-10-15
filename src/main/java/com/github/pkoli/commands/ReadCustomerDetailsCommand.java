package com.github.pkoli.commands;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

/**
 * Created by pkoli on 15/10/17.
 */
public class ReadCustomerDetailsCommand {

    @TargetAggregateIdentifier
    private String customerId;

    public ReadCustomerDetailsCommand(String customerId){
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
