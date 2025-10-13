package com.fabiomijango.gestor_restaurante.validation;

import com.fabiomijango.gestor_restaurante.entity.data.UserRoles;
import com.fabiomijango.gestor_restaurante.service.iUserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserRoleValidator implements ConstraintValidator<ValidUserRole, String> {

    @Autowired
    private iUserService userService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null){
            return false;
        }
        List<String> validRoles = userService.findAllRoles()
                .stream()
                .map(UserRoles::getRole)
                .map(String::toUpperCase)
                .toList();

        return validRoles.contains(value.toUpperCase());
    }
}
