package com.github.pkoli.request;

/**
 * Created by pkoli on 15/10/17.
 */
public class CreateNewAccountRequest {

    private String name;

    private String address;

    /*
    //Uncomment for event upcasting
    private Double salary;

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }*/

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
