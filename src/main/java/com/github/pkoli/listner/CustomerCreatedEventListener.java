package com.github.pkoli.listner;

import com.github.pkoli.aggregates.CustomerAggregate;
import com.github.pkoli.commands.CreateAccountCommand;
import com.github.pkoli.commands.ReadCustomerDetailsCommand;
import com.github.pkoli.events.CustomerCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by pkoli on 15/10/17.
 */
@Component
public class CustomerCreatedEventListener {

    @Autowired
    private CommandGateway commandGateway;

    @Autowired
    private Repository customerRepository;

    @EventHandler
    public void on(CustomerCreatedEvent event){
        commandGateway.send(new CreateAccountCommand(event.getCustomerId()));
    }

    @CommandHandler
    public void on(ReadCustomerDetailsCommand command){
        System.out.println(customerRepository.load(command.getCustomerId()));
    }
}
