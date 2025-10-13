package com.fabiomijango.gestor_restaurante.entity;

import com.fabiomijango.gestor_restaurante.entity.data.Metadata;
import com.fabiomijango.gestor_restaurante.entity.data.OrderState;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Tables table;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User waiter;

    private Double totalPrice;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDish> orderDishes;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "state_id")
    private OrderState state;

    @Embedded
    private Metadata metadata;
}
