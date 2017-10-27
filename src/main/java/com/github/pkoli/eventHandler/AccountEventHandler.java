package com.github.pkoli.eventHandler;

import com.github.pkoli.aggregates.Account;
import com.github.pkoli.events.AccountCreatedEvent;
import com.github.pkoli.events.DeletedAccountEvent;
import com.github.pkoli.repository.AccountQueryRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountEventHandler {

    @Autowired
    private AccountQueryRepository accountQueryRepository;

    @EventHandler
    public void on(AccountCreatedEvent event){
        Account account = new Account();
        account.setCustomerId(event.getCustomerId());
        account.setBalance(event.getBalance());
        account.setId(event.getAccountId());

        accountQueryRepository.save(account);
    }

    @EventHandler
    public void on(DeletedAccountEvent event){
        accountQueryRepository.delete(event.getAccountId());
    }

}
