package com.github.pkoli.aggregates;

import com.github.pkoli.commands.CreateCustomerCommand;
import com.github.pkoli.events.CustomerCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by pkoli on 15/10/17.
 */
@Aggregate
@Entity
@Table(name = "Customer")
public class Customer implements Serializable {

    private static final long serialVersionUID = 2L;

    @Id
    @AggregateIdentifier
    private String customerId;

    private String name;

    private String address;

    public Customer() {
        //Required by JPA
    }

    @CommandHandler
    public Customer(CreateCustomerCommand command) {
        this.name = command.getName();
        this.address = command.getAddress();
        this.customerId = String.valueOf(Math.random()*100);
        AggregateLifecycle.apply(new CustomerCreatedEvent(this));
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
