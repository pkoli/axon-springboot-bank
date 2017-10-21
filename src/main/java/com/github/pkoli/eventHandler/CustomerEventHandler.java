package com.github.pkoli.eventHandler;

import com.github.pkoli.aggregates.Customer;
import com.github.pkoli.commands.CreateAccountCommand;
import com.github.pkoli.commands.DeleteAccountCommand;
import com.github.pkoli.commands.DeleteCustomerCommand;
import com.github.pkoli.events.AccountAddedEvent;
import com.github.pkoli.events.CustomerCreatedEvent;
import com.github.pkoli.events.DeletedCustomerEvent;
import com.github.pkoli.repository.CustomerQueryRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Created by pkoli on 15/10/17.
 */
@Component
public class CustomerEventHandler {

    @Autowired
    private CommandGateway commandGateway;

    @Autowired
    private CustomerQueryRepository customerQueryRepository;

    @EventHandler
    public void on(CustomerCreatedEvent event){
        Customer customer = new Customer();
        customer.setName(event.getName());
        customer.setCustomerId(event.getCustomerId());
        customer.setAddress(event.getAddress());
        customer.setAccountIds(new ArrayList<>());

        customerQueryRepository.save(customer);

    }

    @EventHandler
    public void on(AccountAddedEvent event){
        Customer customer = customerQueryRepository.findOne(event.getCustomerId());

        customer.getAccountIds().add(event.getAccountId());

        customerQueryRepository.save(customer);
    }

    @EventHandler
    public void on(DeletedCustomerEvent event){

        customerQueryRepository.delete(event.getCustomerId());
    }

}