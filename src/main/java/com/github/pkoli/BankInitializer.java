package com.github.pkoli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class BankInitializer {

	public static void main(String[] args) {
        SpringApplication.run(BankInitializer.class, args);
    }
}
