package com.github.pkoli.controller;

import com.github.pkoli.commands.CreateCustomerCommand;
import com.github.pkoli.commands.ReadCustomerDetailsCommand;
import com.github.pkoli.request.CreateAccountRequest;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.model.Repository;
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

    @PostMapping(value = "/addAccount")
    public CompletableFuture addAccount(@RequestBody CreateAccountRequest request){

        return commandGateway.send(new CreateCustomerCommand(request.getName(), request.getAddress()));
    }

    @GetMapping(value = "/getCustomer/{customerId}")
    public CompletableFuture getAccount(@PathVariable String customerId){

        return commandGateway.send(new ReadCustomerDetailsCommand(customerId));

    }
}
