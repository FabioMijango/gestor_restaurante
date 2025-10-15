package com.fabiomijango.gestor_restaurante.entity;

import com.fabiomijango.gestor_restaurante.entity.data.Metadata;
import com.fabiomijango.gestor_restaurante.entity.data.OrderState;
import com.fabiomijango.gestor_restaurante.entity.dto.order.OrderGetDTO;
import jakarta.persistence.*;
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

    @ManyToOne(fetch = FetchType.EAGER)
    private Tables table;

    @ManyToOne( fetch = FetchType.EAGER)
    private User waiter;

    private Double totalPrice;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDish> orderDishes;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "state_id")
    private OrderState state;

    @Embedded
    private Metadata metadata;

    public OrderGetDTO mapToGetDTO() {
        return OrderGetDTO.builder()
                .id(this.getId())
                .stateName(this.getState().getState())
                .waiterEmail(this.getWaiter().getEmail())
                .tableId(this.getTable().getId())
                .totalPrice(this.getTotalPrice())
                .orderDishes(
                        this.getOrderDishes().stream().map( orderDish -> {
                                    return OrderGetDTO.OrderDishDTO.builder()
                                            .id(orderDish.getDish().getId())
                                            .quantity(orderDish.getQuantity())
                                            .build();
                                }
                        ).toList()
                ).build();
    }
}
