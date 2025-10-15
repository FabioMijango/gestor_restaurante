package com.fabiomijango.gestor_restaurante.entity.dto.tables;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TableGetDTO {
    private String id;
    private Integer number;
    private String status;
}
