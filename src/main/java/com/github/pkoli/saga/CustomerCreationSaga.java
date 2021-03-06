package com.github.pkoli.saga;

import com.github.pkoli.commands.CreateAccountCommand;
import com.github.pkoli.events.AccountAddedEvent;
import com.github.pkoli.events.CustomerCreatedEvent;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.saga.SagaEventHandler;
import org.axonframework.eventhandling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import static org.axonframework.eventhandling.saga.SagaLifecycle.associateWith;
import static org.axonframework.eventhandling.saga.SagaLifecycle.end;

@Saga
public class CustomerCreationSaga {

    @Autowired
    private transient CommandGateway commandGateway;

    private String customerId;

    @StartSaga
    @SagaEventHandler(associationProperty = "customerId")
    public void on(CustomerCreatedEvent event){
        associateWith("customerId", event.getCustomerId());

        customerId = event.getCustomerId();
        commandGateway.send(new CreateAccountCommand(event.getCustomerId()));
    }

    @SagaEventHandler(associationProperty = "customerId")
    public void on(AccountAddedEvent event){
        if(event.getCustomerId().equals(customerId)){
            end();
        }
    }

}
