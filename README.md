# axon-springboot-bank
A project created using Axon and Spring boot.

It is a Bank application created using Axon(3.0.6) and Spring boot(1.5.7). It has REST endpoints exposed from where we can 
send post or get requests.

The following functionality has been provided:
  
  - Add a new customer and an account ("/createAccount")
  - Add an account to an existing customer ("/addAccount/{customerId}")
  - Query a customer ("/getCustomer/{customerId}")
  - Delete customer ("/deleteCustomer/{customerId}")
  
Some more details about the configuration of the basic Axon stuff.

On the Command Side we have:

  - InMemory EventStorageEngine
  - Asynchronous CommandBus
  - Default CommandGateway
  - InMemory SagaStore

For the query side I've used InMemory storage again and Hibernate(ORM) along with Spring data for the repositories.

[TODO]: 

  - Expose an endpoint to delete an account of a customer
  - Writing test cases
  - Add Exception Handling

To gain an understanding of Axon and what it is you can start from https://docs.axonframework.org/v/3.0/

I'll be writing a blog pretty soon explaining step by step on how this project was created also about CQRS, DDD, EventSourcing and what role Axon plays in this.
