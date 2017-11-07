package com.github.pkoli.configuration;

import com.github.pkoli.aggregates.Account;
import com.github.pkoli.aggregates.Customer;
import com.github.pkoli.upcaster.CustomerCreatedEventUpcaster;
import org.axonframework.commandhandling.AsynchronousCommandBus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.axonframework.commandhandling.model.GenericJpaRepository;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.saga.repository.SagaStore;
import org.axonframework.eventhandling.saga.repository.inmemory.InMemorySagaStore;
import org.axonframework.eventsourcing.EventCountSnapshotTriggerDefinition;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.Snapshotter;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.eventsourcing.eventstore.jpa.JpaEventStorageEngine;
import org.axonframework.messaging.interceptors.TransactionManagingInterceptor;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.upcasting.event.SingleEventUpcaster;
import org.axonframework.spring.eventsourcing.SpringAggregateSnapshotterFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by pkoli on 15/10/17.
 */
@Configuration
public class BankConfiguration {

    @Bean
    public JpaEventStorageEngine eventStorageEngine(Serializer serializer,
                                                    DataSource dataSource,
                                                    SingleEventUpcaster customerCreatedEventUpcaster,
                                                    EntityManagerProvider entityManagerProvider,
                                                    TransactionManager transactionManager) throws SQLException {
        return new JpaEventStorageEngine(serializer,
                customerCreatedEventUpcaster,
                dataSource,
                entityManagerProvider,
                transactionManager);
    }

    @Bean
    public AsynchronousCommandBus commandBus(TransactionManager transactionManager) {

        AsynchronousCommandBus commandBus = new AsynchronousCommandBus();
        commandBus.registerHandlerInterceptor(new TransactionManagingInterceptor<>(transactionManager));
        return commandBus;
    }

    @Bean
    public CommandGateway commandGateway(TransactionManager transactionManager) {
        return new DefaultCommandGateway(commandBus(transactionManager));
    }

    @Bean
    public SagaStore sagaStore() {
        return new InMemorySagaStore();
    }

    @Bean
    public SingleEventUpcaster customerCreatedEventUpcaster() {
        return new CustomerCreatedEventUpcaster();
    }

    @Bean
    public SpringAggregateSnapshotterFactoryBean snapshotterFactoryBean() {
        return new SpringAggregateSnapshotterFactoryBean();
    }

    @Bean("accountRepository")
    public EventSourcingRepository<Account> accountRepository(EventStore eventStore, Snapshotter snapshotter) {
        return new EventSourcingRepository<>(
                Account.class,
                eventStore,
                new EventCountSnapshotTriggerDefinition(snapshotter, 1));
    }

    @Bean("customerRepository")
    public EventSourcingRepository<Customer> customerRepository(EventStore eventStore, Snapshotter snapshotter) {
        return new EventSourcingRepository<>(
                Customer.class,
                eventStore,
                new EventCountSnapshotTriggerDefinition(snapshotter, 1));
    }

}
