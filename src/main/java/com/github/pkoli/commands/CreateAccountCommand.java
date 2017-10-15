package com.github.pkoli.commands;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

/**
 * Created by pkoli on 15/10/17.
 */
public class CreateAccountCommand {

    @TargetAggregateIdentifier
    private String id;

    private String customerId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CreateAccountCommand(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
