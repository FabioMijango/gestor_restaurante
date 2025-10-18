package com.fabiomijango.gestor_restaurante.controller;

import com.fabiomijango.gestor_restaurante.entity.Dish;
import com.fabiomijango.gestor_restaurante.entity.dto.dish.DishGetDTO;
import com.fabiomijango.gestor_restaurante.entity.dto.dish.DishSaveDTO;
import com.fabiomijango.gestor_restaurante.entity.dto.dish.DishUpdateDTO;
import com.fabiomijango.gestor_restaurante.exception.EntityException.EntityNotValidException;
import com.fabiomijango.gestor_restaurante.service.iDishService;
import com.fabiomijango.gestor_restaurante.util.GenericResponse;
import com.fabiomijango.gestor_restaurante.validation.ValidUUID;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.fabiomijango.gestor_restaurante.security.JwtConfig.SECRET_KEY;
import static com.fabiomijango.gestor_restaurante.security.JwtConfig.getSubjectFromToken;
import static com.fabiomijango.gestor_restaurante.util.Constants.*;

@RestController
@RequestMapping(API_BASE_PATH + DISH_CONTROLLER)
public class DishController implements iGenericCRUDController<DishSaveDTO, DishUpdateDTO> {

    @Autowired
    private iDishService dishService;

    @Override
    @PostMapping
    public ResponseEntity<GenericResponse> save(@Valid @RequestBody DishSaveDTO entity, HttpServletRequest request) {
        dishService.save(entity, getSubjectFromToken(request));
        return GenericResponse.builder()
                .data(null)
                .status(HttpStatus.CREATED)
                .message("Dish saved successfully")
                .build().buildResponse();
    }

    @Override
    @PatchMapping
    public ResponseEntity<GenericResponse> update(@Valid @RequestBody DishUpdateDTO entity, HttpServletRequest request) {
        if(entity.getName() == null &
            entity.getAvailable() == null &
            entity.getDescription() == null &
            entity.getPrice() == null ){
            throw new EntityNotValidException("At least one field must be provided for update");
        }



        dishService.update(entity, getSubjectFromToken(request));

        return GenericResponse.builder()
                .message("Dish updated successfully")
                .data(null)
                .status(HttpStatus.OK)
                .build().buildResponse();
    }

    @Override
    @DeleteMapping(PATH_ID)
    public ResponseEntity<GenericResponse> deleteById(@PathVariable String id) {

        UUID uuid = UUID.fromString(id);
        dishService.deleteById(uuid);
        return GenericResponse.builder()
                .message("Dish deleted successfully")
                .data(null)
                .status(HttpStatus.OK)
                .build().buildResponse();
    }

    @Override
    @GetMapping(PATH_ID)
    public ResponseEntity<GenericResponse> findById(@ValidUUID @PathVariable String id) {

        UUID uuid = UUID.fromString(id);
        DishGetDTO dish = dishService.findById(uuid).orElseThrow(
                () -> new EntityNotFoundException("Dish not found")
        ).mapToDTO();
        return GenericResponse.builder()
                .status(HttpStatus.OK)
                .message("Dish retrieved successfully")
                .data(dish)
                .build().buildResponse();
    }

    @Override
    @GetMapping
    public ResponseEntity<GenericResponse> findAll() {
        List<DishGetDTO> dishes = dishService.findAll()
                .stream().map(Dish::mapToDTO)
                .toList();
        String msg = dishes.isEmpty() ? "No dishes found" : "Dishes retrieved successfully";
        return GenericResponse.builder()
                .status(HttpStatus.OK)
                .data(dishes)
                .message(msg)
                .build().buildResponse();
    }
}
