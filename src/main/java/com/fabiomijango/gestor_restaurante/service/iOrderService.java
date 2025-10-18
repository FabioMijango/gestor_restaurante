package com.fabiomijango.gestor_restaurante.service;

import com.fabiomijango.gestor_restaurante.entity.Order;
import com.fabiomijango.gestor_restaurante.entity.data.OrderState;
import com.fabiomijango.gestor_restaurante.entity.dto.order.OrderSaveDTO;
import com.fabiomijango.gestor_restaurante.entity.dto.order.OrderUpdateDTO;

import java.util.List;
import java.util.UUID;

public interface iOrderService extends iGenericCRUDService<Order, UUID, OrderSaveDTO, OrderUpdateDTO> {

    List<OrderState> findAllOrderStates();

    void closeOrder(UUID orderId, String userName);
}
