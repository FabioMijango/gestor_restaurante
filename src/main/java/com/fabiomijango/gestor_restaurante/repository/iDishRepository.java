package com.fabiomijango.gestor_restaurante.repository;

import com.fabiomijango.gestor_restaurante.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface iDishRepository extends JpaRepository<Dish, UUID> {
    Optional<Dish> findById(UUID id);

    List<Dish> findAll();

    Optional<Dish> findByName(String name);

}
