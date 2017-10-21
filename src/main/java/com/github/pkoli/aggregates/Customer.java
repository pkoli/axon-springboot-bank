package com.github.pkoli.aggregates;

import com.github.pkoli.commands.AddAccountCommand;
import com.github.pkoli.commands.CreateCustomerCommand;
import com.github.pkoli.commands.DeleteCustomerCommand;
import com.github.pkoli.events.AccountAddedEvent;
import com.github.pkoli.events.AccountCreatedEvent;
import com.github.pkoli.events.CustomerCreatedEvent;
import com.github.pkoli.events.DeletedCustomerEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by pkoli on 15/10/17.
 */
@Aggregate
@Entity
@Table(name = "Customer")
public class Customer implements Serializable {

    private static final long serialVersionUID = 2L;

    @Id
    private String customerId;

    private String name;

    private String address;

    private ArrayList<String> accountIds;

    public ArrayList<String> getAccountIds() {
        return accountIds;
    }

    public void setAccountIds(ArrayList<String> accountIds) {
        this.accountIds = accountIds;
    }

    public Customer() {
        //Required by JPA
    }

    @CommandHandler
    public Customer(CreateCustomerCommand command) {
        String customerId = String.valueOf(Integer.parseInt((String.valueOf((int)(Math.random()*100)))));
        AggregateLifecycle.apply(new CustomerCreatedEvent(customerId, command.getName(), command.getAddress()));
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

    @CommandHandler
    public void on(AddAccountCommand command){
        AggregateLifecycle.apply(new AccountAddedEvent(command.getCustomerId(), command.getAccountId()));
    }

    @CommandHandler
    public void on(DeleteCustomerCommand command){
        AggregateLifecycle.apply(new DeletedCustomerEvent(command.getCustomerId()));
    }

    @EventSourcingHandler
    public void on(CustomerCreatedEvent event){
        this.name = event.getName();
        this.address = event.getAddress();
        this.customerId= event.getCustomerId();
        this.accountIds = new ArrayList<>();
    }

    @EventSourcingHandler
    public void on(AccountAddedEvent event){
        this.accountIds.add(event.getAccountId());
    }

    @EventSourcingHandler
    public void on(DeletedCustomerEvent event){
        AggregateLifecycle.markDeleted();
    }

}
