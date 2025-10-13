package com.fabiomijango.gestor_restaurante.controller;

import com.fabiomijango.gestor_restaurante.entity.dto.order.OrderSaveDTO;
import com.fabiomijango.gestor_restaurante.entity.dto.order.OrderUpdateDTO;
import com.fabiomijango.gestor_restaurante.service.iOrderService;
import com.fabiomijango.gestor_restaurante.util.GenericResponse;
import com.fabiomijango.gestor_restaurante.validation.ValidUUID;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.fabiomijango.gestor_restaurante.util.Constants.*;

@RestController
@RequestMapping(API_BASE_PATH + ORDER_CONTROLLER)
public class OrderController implements iGenericCRUDController<OrderSaveDTO, OrderUpdateDTO> {

    @Autowired
    private iOrderService orderService;

    @Override
    @PostMapping
    public ResponseEntity<GenericResponse> save(@Valid @RequestBody OrderSaveDTO entity) {

        orderService.save(entity);
        return GenericResponse.builder()
                .data(null)
                .status(HttpStatus.CREATED)
                .message("Order created successfully")
                .build().buildResponse();
    }

    @Override
    @PatchMapping
    public ResponseEntity<GenericResponse> update(OrderUpdateDTO entity) {
        return null;
    }

    @Override
    @DeleteMapping(PATH_ID)
    public ResponseEntity<GenericResponse> deleteById(String id) {
        return null;
    }

    @Override
    @GetMapping(PATH_ID)
    public ResponseEntity<GenericResponse> findById(@ValidUUID String id) {
        return null;
    }

    @Override
    @GetMapping
    public ResponseEntity<GenericResponse> findAll() {
        return null;
    }
}
