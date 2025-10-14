package com.fabiomijango.gestor_restaurante.validation;

import com.fabiomijango.gestor_restaurante.entity.data.TableState;
import com.fabiomijango.gestor_restaurante.service.iTableService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TableStateValidator implements ConstraintValidator<ValidTableState, String> {

    @Autowired
    private iTableService tableService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            return true;
        }
        List<String> states = tableService.findAllTableStates()
                .stream().map(TableState::getState)
                .toList();
        return states.contains(value);
    }
}
