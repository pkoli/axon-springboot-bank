package com.github.pkoli.validation.constraints;

import com.github.pkoli.validation.CapitalizedValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target( { TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = CapitalizedValidator.class)
public @interface Unique {

    String message() default "{com.github.pkoli.validation.constraints.unique}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}