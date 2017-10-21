package com.github.pkoli.commands;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class AddAccountCommand {

    @TargetAggregateIdentifier
    private String customerId;

    private String accountId;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public AddAccountCommand(String customerId, String accountId) {
        this.customerId = customerId;
        this.accountId = accountId;
    }
}
