package com.fabiomijango.gestor_restaurante.repository;

import com.fabiomijango.gestor_restaurante.entity.Order;
import com.fabiomijango.gestor_restaurante.entity.Tables;
import com.fabiomijango.gestor_restaurante.entity.User;
import com.fabiomijango.gestor_restaurante.entity.data.OrderState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface iOrderRepository extends JpaRepository<Order, UUID> {

    Optional<Order> findById(UUID uuid);

    Page<Order> findAll(Pageable pageable);

    Page<Order> findAllByState(OrderState state, Pageable pageable);

    Page<Order> findAllByWaiterId(User waiterId, Pageable pageable);

//    Page<Order> findAllByPaymentMethod(OrderPaymentMethod paymentMethod, Pageable pageable);

    List<Order> findByTable(Tables table);

}
