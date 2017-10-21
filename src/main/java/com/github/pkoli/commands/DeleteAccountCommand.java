package com.github.pkoli.commands;

public class DeleteAccountCommand {

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
