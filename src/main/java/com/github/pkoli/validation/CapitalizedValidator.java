package com.github.pkoli.validation;

import com.github.pkoli.commands.CreateCustomerCommand;
import com.github.pkoli.repository.CustomerQueryRepository;
import com.github.pkoli.validation.constraints.Unique;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CapitalizedValidator implements ConstraintValidator<Unique, CreateCustomerCommand> {

    @Autowired
    private CustomerQueryRepository customerQueryRepository;

    @Override
    public void initialize(Unique unique) {
    }

    @Override
    public boolean isValid(CreateCustomerCommand createCustomerCommand, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println(customerQueryRepository.findOne("76").getName());
        return createCustomerCommand.getName().equals(customerQueryRepository.findOne("76").getName());
    }
}
