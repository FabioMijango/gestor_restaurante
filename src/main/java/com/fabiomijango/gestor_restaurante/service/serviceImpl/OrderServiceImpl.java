package com.fabiomijango.gestor_restaurante.service.serviceImpl;

import com.fabiomijango.gestor_restaurante.entity.*;
import com.fabiomijango.gestor_restaurante.entity.data.Metadata;
import com.fabiomijango.gestor_restaurante.entity.data.OrderState;
import com.fabiomijango.gestor_restaurante.entity.data.TableState;
import com.fabiomijango.gestor_restaurante.entity.dto.order.OrderSaveDTO;
import com.fabiomijango.gestor_restaurante.entity.dto.order.OrderUpdateDTO;
import com.fabiomijango.gestor_restaurante.entity.dto.orderdish.OrderDishSaveDTO;
import com.fabiomijango.gestor_restaurante.entity.dto.tables.TableUpdateDTO;
import com.fabiomijango.gestor_restaurante.exception.EntityException.EntityNotValidException;
import com.fabiomijango.gestor_restaurante.repository.dataRepository.iOrderStateRepository;
import com.fabiomijango.gestor_restaurante.repository.iOrderRepository;
import com.fabiomijango.gestor_restaurante.service.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderServiceImpl implements iOrderService {

    @Autowired
    private iOrderRepository orderRepository;

    @Autowired
    private iOrderStateRepository orderStateRepository;

    @Autowired
    private iOrderDishService orderDishService;

    @Autowired
    private iTableService tableService;

    @Autowired
    private iDishService dishService;

    @Autowired
    private iUserService userService;


    @Override
    public List<OrderState> findAllOrderStates() {
        return orderStateRepository.findAll();
    }

    @Override
    @Transactional
    public void save(OrderSaveDTO entity) {
        UUID tableId = UUID.fromString(entity.getTableId());
        Tables table = tableService.findById(tableId).orElseThrow(
                () -> new EntityNotFoundException("Table not found")
        );
        if (table.getState().getState().equals("Occupied")) {
            throw new EntityNotValidException("Table is already occupied");
        }

        tableService.update(
                TableUpdateDTO.builder()
                    .id(entity.getTableId())
                    .newState("Occupied")
                    .build()
                );

        User waiter = userService.findByEmail(entity.getWaiterEmail()).orElseThrow(
                () -> new EntityNotFoundException("Waiter not found")
        );
        OrderState orderState = orderStateRepository.findByState("Pending").orElseThrow(
                () -> new EntityNotFoundException("Order state 'Pending' not found")
        );

        double totalPrice = 0.0;

        Order order = new Order();
        order.setTable(table);
        order.setWaiter(waiter);
        order.setState(orderState);
        order.setOrderDishes(
                entity.getDishes().stream().map(
                        dishDTO -> {
                            UUID uuid = UUID.fromString(dishDTO.getDishId());
                            Dish dish = dishService.getDishById(uuid).orElseThrow(
                                    () -> new EntityNotFoundException("Dish not found")
                            );

                            OrderDish orderDish = new OrderDish();
                            orderDish.setDish(dish);
                            orderDish.setQuantity(dishDTO.getQuantity());
                            orderDish.setOrder(order);
                            orderDish.setMetadata(new Metadata());

                            return orderDish;
                        }
                ).toList()
        );

        for(OrderDish orderDish : order.getOrderDishes()) {
            totalPrice += orderDish.getDish().getPrice() * orderDish.getQuantity();
        }
        order.setTotalPrice(totalPrice);
        order.setMetadata(new Metadata());

        orderRepository.save(order);
    }

    @Override
    @Transactional
    public void update(OrderUpdateDTO entity) {
        UUID orderId = UUID.fromString(entity.getOrderId());
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new EntityNotFoundException("Order not found")
        );
        if(entity.getOrderState() != null){
            OrderState orderState = orderStateRepository.findByState(entity.getOrderState()).orElseThrow(
                    () -> new EntityNotFoundException("Order state not found or not valid")
            );
            order.setState(orderState);
        }
        if(entity.getDishes() != null) {
            List<OrderDish> newDishes = entity.getDishes().stream()
                .map( dishDTO -> {
                    UUID uuid = UUID.fromString(dishDTO.getDishId());
                    Dish dish = dishService.getDishById(uuid).orElseThrow(
                            () -> new EntityNotFoundException("Dish not found")
                    );

                    OrderDish orderDish = new OrderDish();
                    orderDish.setDish(dish);
                    orderDish.setQuantity(dishDTO.getQuantity());
                    orderDish.setOrder(order);
                    orderDish.setMetadata(new Metadata());

                    return orderDish;
                }).toList();

            order.setOrderDishes(newDishes);
        }


    }

    @Override
    public void deleteById(UUID uuid) {
        orderRepository.deleteById(uuid);
    }

    @Override
    public Optional<Order> findById(UUID uuid) {
        return orderRepository.findById(uuid);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

}
