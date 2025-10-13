package com.fabiomijango.gestor_restaurante.service;

import com.fabiomijango.gestor_restaurante.entity.Dish;
import com.fabiomijango.gestor_restaurante.entity.dto.dish.DishSaveDTO;
import com.fabiomijango.gestor_restaurante.entity.dto.dish.DishUpdateDTO;

import java.util.Optional;
import java.util.UUID;


public interface iDishService extends iGenericCRUDService<Dish, UUID, DishSaveDTO, DishUpdateDTO> {

    Optional<Dish> getDishByName(String name);

    Optional<Dish> getDishById(UUID id);

}
