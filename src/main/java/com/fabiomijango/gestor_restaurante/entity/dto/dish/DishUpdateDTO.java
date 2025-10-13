package com.fabiomijango.gestor_restaurante.entity.dto.dish;

import com.fabiomijango.gestor_restaurante.validation.ValidUUID;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.UUID;

@Getter
public class DishUpdateDTO {
    @NotBlank(message = "ID is mandatory")
    @ValidUUID(message = "ID must be a valid UUID")
    private String id;

    @Size(max = 50, message = "Name must be at most 50 characters")
    private String name;

    @Size(max = 200, message = "Description must be at most 200 characters")
    private String description;

    @Min(value = 0, message = "Price must be greater than or equal to 0")
    private Double price;


    private Boolean available;
}
