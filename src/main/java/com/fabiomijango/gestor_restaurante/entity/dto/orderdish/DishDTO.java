package com.fabiomijango.gestor_restaurante.entity.dto.orderdish;

import com.fabiomijango.gestor_restaurante.validation.ValidUUID;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class DishDTO {
    @NotBlank(message = "Dish ID is mandatory")
    @ValidUUID
    private String dishId;
    @NotNull(message = "Dish quantity is mandatory")
    @Min(value = 1, message = "Dish quantity must be at least 1")
    private Integer quantity;
}
