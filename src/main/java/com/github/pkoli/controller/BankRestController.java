package com.github.pkoli.controller;

import com.github.pkoli.aggregates.Customer;
import com.github.pkoli.commands.CreateAccountCommand;
import com.github.pkoli.commands.CreateCustomerCommand;
import com.github.pkoli.commands.DeleteCustomerCommand;
import com.github.pkoli.repository.AccountQueryRepository;
import com.github.pkoli.repository.CustomerQueryRepository;
import com.github.pkoli.request.CreateNewAccountRequest;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

/**
 * Created by pkoli on 15/10/17.
 */
@RestController
public class BankRestController {

    @Autowired
    private CommandGateway commandGateway;

    @Autowired
    private CustomerQueryRepository customerQueryRepository;

    @Autowired
    private AccountQueryRepository accountQueryRepository;

    @PostMapping(value = "/createAccount")
    public CompletableFuture createAccount(@RequestBody CreateNewAccountRequest request){

        return commandGateway.send(new CreateCustomerCommand(request.getName(), request.getAddress()));
    }

    @GetMapping(value = "/getCustomer/{customerId}")
    public ResponseEntity<Customer> getAccount(@PathVariable String customerId){

        return new ResponseEntity<>(customerQueryRepository.findOne(customerId), HttpStatus.OK);
    }

    @PostMapping(value = "/addAccount/{customerId}")
    public CompletableFuture addAccount(@PathVariable String customerId){

        return commandGateway.send(new CreateAccountCommand(customerId));
    }

    @PostMapping(value = "/deleteCustomer/{customerId}")
    public ResponseEntity deleteCustomer(@PathVariable String customerId){

        commandGateway.send(new DeleteCustomerCommand(customerId));
        return new ResponseEntity(HttpStatus.OK);
    }
}
