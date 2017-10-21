package com.github.pkoli.aggregates;

import com.github.pkoli.commands.AddAccountCommand;
import com.github.pkoli.commands.CreateAccountCommand;
import com.github.pkoli.commands.DeleteAccountCommand;
import com.github.pkoli.events.AccountAddedEvent;
import com.github.pkoli.events.AccountCreatedEvent;
import com.github.pkoli.events.DeletedAccountEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventsourcing.EventSourcingHandler;
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
@Table(name = "Account")
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private double balance;

    private String customerId;

    public Account() {
        //Required by JPA
    }

    @CommandHandler
    public Account(CreateAccountCommand command) {
        String id = String.valueOf(Integer.parseInt(String.valueOf((int)(Math.random()*100))));
        double balance = 0;

        AggregateLifecycle.apply(new AccountCreatedEvent(command.getCustomerId(), id, balance));
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

    @CommandHandler
    public Account(DeleteAccountCommand command) {
        AggregateLifecycle.apply(new DeletedAccountEvent(command.getAccountId()));
    }

    @EventSourcingHandler
    public void on(AccountCreatedEvent event){
        this.id = event.getAccountId();
        this.customerId = event.getCustomerId();
        this.balance = event.getBalance();
    }

    @EventSourcingHandler
    public void on(DeletedAccountEvent event){
        AggregateLifecycle.markDeleted();
    }

}