package com.github.pkoli.repository;

import com.github.pkoli.aggregates.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountQueryRepository extends CrudRepository<Account, String> {
}
