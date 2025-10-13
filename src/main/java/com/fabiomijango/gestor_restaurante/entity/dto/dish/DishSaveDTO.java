package com.fabiomijango.gestor_restaurante.entity.dto.dish;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class DishSaveDTO {
    @NotBlank(message = "Name is mandatory")
    @Size(min = 1, max = 50, message = "Name must be between 1 and 50 characters")
    private String name;

    @Size(min = 1, max = 100, message = "Description must be between 1 and 100 characters")
    private String description;

    @Min(value = 1, message = "Price must be greater than or equal to 1")
    @NotNull(message = "Price is mandatory")
    private Double price;

    @NotBlank(message = "Dish category is mandatory")
    private String dishCategory;
}
