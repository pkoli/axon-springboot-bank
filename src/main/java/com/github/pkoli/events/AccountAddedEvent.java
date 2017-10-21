package com.github.pkoli.events;

public class AccountAddedEvent {

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

    public AccountAddedEvent(String customerId, String accountId) {
        this.customerId = customerId;
        this.accountId = accountId;
    }
}