package com.github.pkoli.aggregates;

import com.github.pkoli.commands.CreateCustomerCommand;
import com.github.pkoli.events.CustomerCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import java.io.Serializable;

/**
 * Created by pkoli on 15/10/17.
 */
@Aggregate
public class CustomerAggregate implements Serializable {

    private static final long serialVersionUID = 2L;

    @AggregateIdentifier
    private String customerId;

    private String name;

    private String address;

    public CustomerAggregate(){
        //Required by JPA
    }

    @CommandHandler
    public CustomerAggregate(CreateCustomerCommand command){
        this.name = command.getName();
        this.address = command.getAddress();
        this.customerId = String.valueOf(Math.random()*100);
        AggregateLifecycle.apply(new CustomerCreatedEvent(customerId));
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

    @EventSourcingHandler
    public void on(CustomerCreatedEvent event){
        this.customerId = event.getCustomerId();
    }

}
