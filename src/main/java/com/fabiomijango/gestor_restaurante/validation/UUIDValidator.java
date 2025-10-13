package com.fabiomijango.gestor_restaurante.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class UUIDValidator implements ConstraintValidator<ValidUUID, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        Pattern uuidPattern = Pattern.compile("^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$");
        return uuidPattern.matcher(value).matches();
    }
}
