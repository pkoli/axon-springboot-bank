package com.github.pkoli.commands;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class DeleteAccountCommand {

    @TargetAggregateIdentifier
    private String accountId;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public DeleteAccountCommand(String accountId) {
        this.accountId = accountId;
    }
}
