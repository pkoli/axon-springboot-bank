package com.github.pkoli.repository;

import com.github.pkoli.aggregates.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, String> {
}
