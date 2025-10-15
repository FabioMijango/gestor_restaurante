package com.fabiomijango.gestor_restaurante.entity.dto.order;

import com.fabiomijango.gestor_restaurante.entity.dto.orderdish.DishDTO;
import com.fabiomijango.gestor_restaurante.validation.ValidUUID;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderSaveDTO {
    @NotBlank(message = "Table ID is mandatory")
    @ValidUUID(message = "Table ID must be a valid UUID")
    private String tableId;

    @NotBlank(message = "Waiter email is mandatory")
    @Email(message = "Waiter email must be a valid email address")
    private String waiterEmail;

    private List<DishDTO> dishes;

}

