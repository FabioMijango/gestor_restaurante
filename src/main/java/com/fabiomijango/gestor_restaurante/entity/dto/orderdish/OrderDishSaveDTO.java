package com.fabiomijango.gestor_restaurante.entity.dto.orderdish;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;

@Data
public class OrderDishSaveDTO {
    @NotEmpty
    @Min(value = 1)
    private Integer quantity;

    @NotEmpty
    private String dishId;

    @NotEmpty
    private String orderId;
}
