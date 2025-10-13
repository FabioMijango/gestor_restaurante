package com.fabiomijango.gestor_restaurante.validation;

import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TableStateValidator.class)
public @interface ValidTableState {
    String message() default "Invalid table state";

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};
}
