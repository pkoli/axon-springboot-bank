package com.github.pkoli.commands;

public class DeleteCustomerCommand {

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
