package com.fabiomijango.gestor_restaurante.entity.dto.order;

import com.fabiomijango.gestor_restaurante.validation.ValidOrderState;
import com.fabiomijango.gestor_restaurante.validation.ValidUUID;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderUpdateDTO {
    @NotNull
    @ValidUUID
    private String orderId;

    @ValidOrderState
    private String orderState;

    private List<DishDTO> dishes;

    @Getter
    public static class DishDTO {
        @NotBlank(message = "Dish ID is mandatory")
        @ValidUUID
        private String dishId;
        @NotNull(message = "Dish quantity is mandatory")
        @Min(value = 1, message = "Dish quantity must be at least 1")
        private Integer quantity;
    }
}
