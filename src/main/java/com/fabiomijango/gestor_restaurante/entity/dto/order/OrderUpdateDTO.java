package com.fabiomijango.gestor_restaurante.entity.dto.order;

import com.fabiomijango.gestor_restaurante.entity.dto.orderdish.DishDTO;
import com.fabiomijango.gestor_restaurante.validation.ValidOrderState;
import com.fabiomijango.gestor_restaurante.validation.ValidUUID;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderUpdateDTO {
    @NotNull(message = "Order ID is mandatory")
    @ValidUUID(message = "Order ID must be a valid UUID")
    private String orderId;

    @ValidOrderState(message = "Order state is not valid")
    private String orderState;

    private List<DishDTO> dishes;

}
