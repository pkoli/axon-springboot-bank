package com.github.pkoli.configuration;

import com.github.pkoli.aggregates.Customer;
import org.axonframework.commandhandling.AsynchronousCommandBus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by pkoli on 15/10/17.
 */
@Configuration
public class BankConfiguration {

    @Bean
    public EventStorageEngine eventStorageEngine(){
        return new InMemoryEventStorageEngine();
    }

    @Bean
    public AsynchronousCommandBus commandBus(){
        return new AsynchronousCommandBus();
    }

    @Bean
    public CommandGateway commandGateway(){
        return new DefaultCommandGateway(commandBus());
    }

    @Bean
    public EventSourcingRepository<Customer> customerRepository() {
        EventStore eventStore = new EmbeddedEventStore(eventStorageEngine());
        EventSourcingRepository<Customer> repository = new EventSourcingRepository<>(Customer.class, eventStore);
        return repository;
    }

}
