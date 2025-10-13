package com.fabiomijango.gestor_restaurante.repository.dataRepository;

import com.fabiomijango.gestor_restaurante.entity.data.OrderState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface iOrderStateRepository extends JpaRepository<OrderState, Long> {
    boolean existsByState(String state);

    Optional<OrderState> findByState(String state);

    List<OrderState> findAll();
}
