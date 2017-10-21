package com.github.pkoli.commands;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class DeleteCustomerCommand {

    @TargetAggregateIdentifier
    private String customerId;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public DeleteCustomerCommand(String customerId) {
        this.customerId = customerId;
    }
}
