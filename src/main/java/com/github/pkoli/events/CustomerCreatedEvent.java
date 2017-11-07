package com.github.pkoli.events;

import org.axonframework.serialization.Revision;

/**
 * Created by pkoli on 15/10/17.
 */
@Revision(value = "1")
public class CustomerCreatedEvent {

    private String customerId;
    private String name;
    private String address;

    public CustomerCreatedEvent(String customerId, String name, String address) {
        this.customerId = customerId;
        this.name = name;
        this.address = address;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
/*
@Revision(value = "2")
public class CustomerCreatedEvent {

    private String customerId;
    private String name;
    private String address;
    private long salary;

    public CustomerCreatedEvent(String customerId, String name, String address, long salary) {
        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.salary = salary;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSalary(){
        this.salary = salary;
    }

    public long getSalary(){
        return salary;
    }
}
 */
