package com.fabiomijango.gestor_restaurante.service;

import com.fabiomijango.gestor_restaurante.entity.OrderDish;
import com.fabiomijango.gestor_restaurante.entity.dto.orderdish.OrderDishSaveDTO;
import com.fabiomijango.gestor_restaurante.entity.dto.orderdish.OrderDishUpdateDTO;

import java.util.List;
import java.util.UUID;

public interface iOrderDishService extends iGenericCRUDService<OrderDish, UUID, OrderDishSaveDTO, OrderDishUpdateDTO> {
    void deleteAllOrderDishesByOrderId(UUID orderId);

    List<OrderDish> findAllByOrderId(UUID orderId);
}
