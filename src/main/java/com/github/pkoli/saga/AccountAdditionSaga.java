package com.github.pkoli.saga;

import com.github.pkoli.commands.AddAccountCommand;
import com.github.pkoli.events.AccountAddedEvent;
import com.github.pkoli.events.AccountCreatedEvent;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.saga.SagaEventHandler;
import org.axonframework.eventhandling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import static org.axonframework.eventhandling.saga.SagaLifecycle.associateWith;
import static org.axonframework.eventhandling.saga.SagaLifecycle.end;

@Saga
public class AccountAdditionSaga {

    @Autowired
    private transient CommandGateway commandGateway;

    private String accountId;
    private String customerId;

    @StartSaga
    @SagaEventHandler(associationProperty = "customerId")
    public void on(AccountCreatedEvent event){
        accountId = event.getAccountId();
        customerId = event.getCustomerId();

        associateWith("accountId", accountId);

        commandGateway.send(new AddAccountCommand(event.getCustomerId(), event.getAccountId()));
    }

    @SagaEventHandler(associationProperty = "accountId")
    public void on(AccountAddedEvent event){
        if(event.getCustomerId().equals(customerId) && event.getAccountId().equals(accountId)){
            end();
        }
    }




}
