package com.github.pkoli.events;

/**
 * Created by pkoli on 15/10/17.
 */
public class AccountCreatedEvent {

    private String customerId;

    private String accountId;

    public AccountCreatedEvent(String customerId, String accountId){
        this.customerId = customerId;
        this.accountId = accountId;
    }

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
}
