package com.fabiomijango.gestor_restaurante.entity.dto.tables;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class TableSaveDTO {
    @NotNull(message = "Table number cannot be null")
    @Min(value = 0, message = "Table number must be greater than or equal to 0")
    private Integer tableNumber;
}
