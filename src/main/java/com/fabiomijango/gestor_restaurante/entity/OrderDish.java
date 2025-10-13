package com.fabiomijango.gestor_restaurante.entity;

import com.fabiomijango.gestor_restaurante.entity.data.Metadata;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "order_dish")
public class OrderDish {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "dish_id", nullable = false)
    private Dish dish;

    @Min(value = 0)
    private Integer quantity;

    @Embedded
    private Metadata metadata;
}
