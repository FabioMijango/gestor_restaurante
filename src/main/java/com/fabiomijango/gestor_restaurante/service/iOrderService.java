package com.fabiomijango.gestor_restaurante.service;

import com.fabiomijango.gestor_restaurante.entity.Order;
import com.fabiomijango.gestor_restaurante.entity.OrderDish;
import com.fabiomijango.gestor_restaurante.entity.data.OrderState;
import com.fabiomijango.gestor_restaurante.entity.dto.order.OrderSaveDTO;
import com.fabiomijango.gestor_restaurante.entity.dto.order.OrderUpdateDTO;

import java.util.List;
import java.util.UUID;

public interface iOrderService extends iGenericCRUDService<Order, UUID, OrderSaveDTO, OrderUpdateDTO> {

//    void deleteAllOrderDishesByOrderId(UUID orderId);
//
//    List<OrderDish> findAllOrderDishesByOrderId(UUID orderId);

    List<OrderState> findAllOrderStates();
}
