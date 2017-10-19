package com.github.pkoli.eventHandler;

import com.github.pkoli.commands.CreateAccountCommand;
import com.github.pkoli.commands.ReadCustomerDetailsCommand;
import com.github.pkoli.events.CustomerCreatedEvent;
import com.github.pkoli.repository.CustomerRepository;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by pkoli on 15/10/17.
 */
@Component
public class CustomerCreatedEventHandler {

    @Autowired
    private CommandGateway commandGateway;

    @Autowired
    private CustomerRepository customerRepository;

    @EventHandler
    public void on(CustomerCreatedEvent event){
        commandGateway.send(new CreateAccountCommand(event.getCustomer().getCustomerId()));
    }

    @CommandHandler
    public void on(ReadCustomerDetailsCommand command){
        System.out.println(customerRepository.findOne(command.getCustomerId()));
    }
}
