package com.fabiomijango.gestor_restaurante.validation;

import com.fabiomijango.gestor_restaurante.entity.data.OrderState;
import com.fabiomijango.gestor_restaurante.service.iOrderService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrderStateValidator implements ConstraintValidator<ValidOrderState, String> {

    @Autowired
    private iOrderService orderService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        List<String> orderStates = orderService.findAllOrderStates().stream().map(OrderState::getState).toList();

        return orderStates.contains(value);
    }
}
