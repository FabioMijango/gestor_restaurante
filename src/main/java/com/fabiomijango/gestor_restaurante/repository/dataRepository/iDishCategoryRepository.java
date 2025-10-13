package com.fabiomijango.gestor_restaurante.repository.dataRepository;


import com.fabiomijango.gestor_restaurante.entity.data.DishCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface iDishCategoryRepository extends JpaRepository<DishCategory, Long> {
    boolean existsByCategory(String category);

    Optional<DishCategory> findByCategory(String category);

    List<DishCategory> findAll();
}
