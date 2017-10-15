package com.github.pkoli;

import com.github.pkoli.aggregates.AccountAggregate;
import com.github.pkoli.commands.CreateAccountCommand;
import com.github.pkoli.events.AccountCreatedEvent;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

public class Bank {

	/*FixtureConfiguration<AccountAggregate> fixture;

	@Before
	public void setup(){
		fixture = new AggregateTestFixture(AccountAggregate.class);
	}

	@Test
	public void testHandlingOfCreateAccountCommand(){
		fixture.given()
				.when(new CreateAccountCommand("1"))
				.expectEvents(new AccountCreatedEvent("1", "123"));
	}*/

}
