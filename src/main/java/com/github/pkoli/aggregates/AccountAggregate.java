package com.github.pkoli.aggregates;

import com.github.pkoli.commands.CreateAccountCommand;
import com.github.pkoli.events.AccountCreatedEvent;
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
public class AccountAggregate implements Serializable {

    private static final long serialVersionUID = 1L;

    @AggregateIdentifier
    private String id;

    private double balance;

    private String customerId;

    public AccountAggregate(){
        //Required by JPA
    }

    @CommandHandler
    public AccountAggregate(CreateAccountCommand command){

        this.customerId = command.getCustomerId();
        this.balance = 0;

        AggregateLifecycle.apply(new AccountCreatedEvent(command.getCustomerId(),
                String.valueOf(Math.random()*100)));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @EventSourcingHandler
    public void on(AccountCreatedEvent event){
        this.id = event.getAccountId();
    }

}