package com.fabiomijango.gestor_restaurante.controller;

import com.fabiomijango.gestor_restaurante.entity.Order;
import com.fabiomijango.gestor_restaurante.entity.dto.order.OrderGetDTO;
import com.fabiomijango.gestor_restaurante.entity.dto.order.OrderSaveDTO;
import com.fabiomijango.gestor_restaurante.entity.dto.order.OrderUpdateDTO;
import com.fabiomijango.gestor_restaurante.exception.EntityException.EntityNotValidException;
import com.fabiomijango.gestor_restaurante.service.iOrderService;
import com.fabiomijango.gestor_restaurante.util.GenericResponse;
import com.fabiomijango.gestor_restaurante.validation.ValidUUID;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.fabiomijango.gestor_restaurante.util.Constants.*;

@RestController
@RequestMapping(API_BASE_PATH + ORDER_CONTROLLER)
public class OrderController implements iGenericCRUDController<OrderSaveDTO, OrderUpdateDTO> {

    @Autowired
    private iOrderService orderService;

    @Override
    @PostMapping
    public ResponseEntity<GenericResponse> save(@Valid @RequestBody OrderSaveDTO entity, HttpServletRequest request) {

        orderService.save(entity);
        return GenericResponse.builder()
                .data(null)
                .status(HttpStatus.CREATED)
                .message("Order created successfully")
                .build().buildResponse();
    }

    @Override
    @PatchMapping
    public ResponseEntity<GenericResponse> update(@Valid @RequestBody OrderUpdateDTO entity, HttpServletRequest request) {
        if(entity.getDishes() == null & entity.getOrderState() == null){
            throw new EntityNotValidException("At least one field must be provided for update");
        }

        orderService.update(entity);
        return GenericResponse.builder()
                .data(null)
                .status(HttpStatus.OK)
                .message("Order updated successfully")
                .build().buildResponse();
    }

    @Override
    @DeleteMapping(PATH_ID)
    public ResponseEntity<GenericResponse> deleteById(@ValidUUID @PathVariable String id) {
        UUID uuid = UUID.fromString(id);
        orderService.deleteById(uuid);

        return GenericResponse.builder()
                .status(HttpStatus.OK)
                .data(null)
                .message("Order deleted successfully")
                .build().buildResponse();
    }

    @Override
    @GetMapping(PATH_ID)
    public ResponseEntity<GenericResponse> findById(@ValidUUID @PathVariable String id) {
        UUID uuid = UUID.fromString(id);
        OrderGetDTO order = orderService.findById(uuid).orElseThrow(
                () -> new EntityNotFoundException("Order not found")
        ).mapToGetDTO();
        return GenericResponse.builder()
                .status(HttpStatus.OK)
                .data(order)
                .message("Order retrieved successfully")
                .build().buildResponse();
    }

    @Override
    @GetMapping
    public ResponseEntity<GenericResponse> findAll() {
        List<OrderGetDTO> orders = orderService.findAll()
                .stream().map(Order::mapToGetDTO)
                .toList();
        String msg = orders.isEmpty() ? "No orders found" : "Orders retrieved successfully";
        return GenericResponse.builder()
                .status(HttpStatus.OK)
                .data(orders)
                .message(msg)
                .build().buildResponse();
    }
}
