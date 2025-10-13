package com.fabiomijango.gestor_restaurante.entity.data;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "table_states")
public class TableState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String state;
}
