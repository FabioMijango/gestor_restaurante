package com.fabiomijango.gestor_restaurante.entity.dto.tables;

import com.fabiomijango.gestor_restaurante.validation.ValidTableState;
import com.fabiomijango.gestor_restaurante.validation.ValidUUID;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
public class TableUpdateDTO {

    @NotBlank(message = "ID is mandatory")
    @ValidUUID(message = "ID must be a valid UUID")
    private String id;

    @NotNull(message = "New state is mandatory")
    @ValidTableState(message = "Invalid table state")
    private String newState;
}
