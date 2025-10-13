package com.fabiomijango.gestor_restaurante.entity.data;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "order_states")
public class OrderState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String state;
}
