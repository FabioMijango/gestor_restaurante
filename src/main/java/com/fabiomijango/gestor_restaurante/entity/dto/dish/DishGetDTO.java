package com.fabiomijango.gestor_restaurante.entity.dto.dish;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DishGetDTO {
    private String id;
    private String category;
    private String name;
    private String description;
    private Double price;
    private Boolean isAvailable;
}
