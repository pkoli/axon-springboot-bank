package com.github.pkoli.events;

public class DeletedAccountEvent {

    private String accountId;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public DeletedAccountEvent(String accountId) {
        this.accountId = accountId;
    }
}
