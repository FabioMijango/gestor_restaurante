package com.fabiomijango.gestor_restaurante.repository;

import com.fabiomijango.gestor_restaurante.entity.Order;
import com.fabiomijango.gestor_restaurante.entity.OrderDish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface iOrderDishRepository extends JpaRepository<OrderDish, UUID> {

    Optional<OrderDish> findById(UUID id);

    List<OrderDish> findAllByOrder(Order order);

    List<OrderDish> findAllByOrder_Id(UUID orderId);

    void deleteAllByOrder_Id(UUID orderId);
}
