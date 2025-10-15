package com.fabiomijango.gestor_restaurante.service.serviceImpl;

import com.fabiomijango.gestor_restaurante.entity.Dish;
import com.fabiomijango.gestor_restaurante.entity.data.DishCategory;
import com.fabiomijango.gestor_restaurante.entity.data.Metadata;
import com.fabiomijango.gestor_restaurante.entity.dto.dish.DishSaveDTO;
import com.fabiomijango.gestor_restaurante.entity.dto.dish.DishUpdateDTO;
import com.fabiomijango.gestor_restaurante.repository.dataRepository.iDishCategoryRepository;
import com.fabiomijango.gestor_restaurante.repository.iDishRepository;
import com.fabiomijango.gestor_restaurante.service.iDishService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DishServiceImpl implements iDishService {

    @Autowired
    private iDishRepository dishRepository;

    @Autowired
    private iDishCategoryRepository dishCategoryRepository;

    @Override
    public Optional<Dish> getDishByName(String name) {
        return dishRepository.findByName(name);
    }

    @Override
    public Optional<Dish> getDishById(UUID id) {
        return dishRepository.findById(id);
    }


    @Override
    public void save(DishSaveDTO entity) {
        DishCategory dishCategory = dishCategoryRepository.findByCategory(entity.getDishCategory()).orElseThrow(
                () -> new EntityNotFoundException("Dish category not found")
        );

        Dish dish = new Dish();
        dish.setName(entity.getName());
        dish.setDescription(entity.getDescription() == null ? "" : entity.getDescription());
        dish.setPrice(entity.getPrice());
        dish.setCategory(dishCategory);
        dish.setIsAvailable(true);
        dish.setMetadata(new Metadata());

        dishRepository.save(dish);
    }

    @Override
    public void update(DishUpdateDTO entity) {
        UUID uuid = UUID.fromString(entity.getId());
        Dish dish = dishRepository.findById(uuid).orElseThrow(
                () -> new EntityNotFoundException("Dish not found")
        );

        if(entity.getName() != null & !entity.getName().isBlank()){
            dish.setName(entity.getName());
        }
        if(entity.getDescription() != null){
            dish.setDescription(entity.getDescription());
        }
        if(entity.getPrice() != null){
            dish.setPrice(entity.getPrice());
        }
        if(entity.getAvailable() != null){
            dish.setIsAvailable(entity.getAvailable());
        }

        Metadata md = dish.getMetadata();
        md.updateMetadata(""); // TODO: get User
        dish.setMetadata(md);

        dishRepository.save(dish);
    }

    @Override
    public void deleteById(UUID uuid) {
        dishRepository.deleteById(uuid);
    }

    @Override
    public Optional<Dish> findById(UUID uuid) {
        return dishRepository.findById(uuid);
    }

    @Override
    public List<Dish> findAll() {
        return dishRepository.findAll();
    }
}
