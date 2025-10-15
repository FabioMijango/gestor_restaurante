package com.fabiomijango.gestor_restaurante.entity.dto.order;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class OrderGetDTO {
    private UUID id;
    private UUID tableId;
    private String waiterEmail;
    private Double totalPrice;
    private String stateName;
    private List<OrderDishDTO> orderDishes;

    @Data
    @Builder
    public static class OrderDishDTO {
        private UUID id;
        private Integer quantity;
    }
}
