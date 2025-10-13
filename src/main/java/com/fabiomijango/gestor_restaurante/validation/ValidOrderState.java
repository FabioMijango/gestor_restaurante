package com.fabiomijango.gestor_restaurante.validation;

import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OrderStateValidator.class)
public @interface ValidOrderState {
    String message() default "Invalid order state";

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};
}
