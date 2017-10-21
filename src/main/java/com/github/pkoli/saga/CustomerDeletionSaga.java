package com.github.pkoli.saga;

import com.github.pkoli.aggregates.Customer;
import com.github.pkoli.commands.DeleteAccountCommand;
import com.github.pkoli.events.DeletedAccountEvent;
import com.github.pkoli.events.DeletedCustomerEvent;
import com.github.pkoli.repository.CustomerQueryRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.saga.SagaEventHandler;
import org.axonframework.eventhandling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

import static org.axonframework.eventhandling.saga.SagaLifecycle.associateWith;
import static org.axonframework.eventhandling.saga.SagaLifecycle.end;

@Saga
public class CustomerDeletionSaga {

    @Autowired
    private transient CustomerQueryRepository customerQueryRepository;

    @Autowired
    private transient CommandGateway commandGateway;

    private ArrayList<String> accountIds;

    @StartSaga
    @SagaEventHandler(associationProperty = "customerId")
    public void on(DeletedCustomerEvent event){
        Customer customer = customerQueryRepository.findOne(event.getCustomerId());

        accountIds = customer.getAccountIds();

        for(int i=0; i< customer.getAccountIds().size(); i++){
            associateWith("accountId", customer.getAccountIds().get(i));
            commandGateway.send(new DeleteAccountCommand(customer.getAccountIds().get(i)));
        }
    }

    @SagaEventHandler(associationProperty = "accountId")
    public void on(DeletedAccountEvent event){
        accountIds.remove(event.getAccountId());

        if(accountIds.isEmpty()){
            end();
        }
    }
}
