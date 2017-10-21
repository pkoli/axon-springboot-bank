package com.github.pkoli.events;

/**
 * Created by pkoli on 15/10/17.
 */
public class AccountCreatedEvent {

    private String customerId;
    private String accountId;
    private double balance;

    public AccountCreatedEvent(String customerId, String accountId, double balance){
        this.customerId = customerId;
        this.accountId = accountId;
        this.balance = balance;
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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
