package com.fabiomijango.gestor_restaurante.service.serviceImpl;

import com.fabiomijango.gestor_restaurante.entity.*;
import com.fabiomijango.gestor_restaurante.entity.data.Metadata;
import com.fabiomijango.gestor_restaurante.entity.data.OrderState;
import com.fabiomijango.gestor_restaurante.entity.dto.order.OrderSaveDTO;
import com.fabiomijango.gestor_restaurante.entity.dto.order.OrderUpdateDTO;
import com.fabiomijango.gestor_restaurante.entity.dto.orderdish.DishDTO;
import com.fabiomijango.gestor_restaurante.entity.dto.tables.TableUpdateDTO;
import com.fabiomijango.gestor_restaurante.exception.EntityException.EntityNotValidException;
import com.fabiomijango.gestor_restaurante.repository.dataRepository.iOrderStateRepository;
import com.fabiomijango.gestor_restaurante.repository.iOrderRepository;
import com.fabiomijango.gestor_restaurante.service.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    public void save(OrderSaveDTO entity, String userName) {
        UUID tableId = UUID.fromString(entity.getTableId());
        Tables table = tableService.findById(tableId).orElseThrow(
                () -> new EntityNotFoundException("Table not found")
        );
        if (!table.getState().getState().equals("Available")) {
            throw new EntityNotValidException("Table is not available");
        }

        tableService.update(
                TableUpdateDTO.builder()
                    .id(entity.getTableId())
                    .newState("Occupied")
                    .build(),
                userName
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
                mapToOrderDishes(entity.getDishes(), order, userName)
        );

        for(OrderDish orderDish : order.getOrderDishes()) {
            totalPrice += orderDish.getDish().getPrice() * orderDish.getQuantity();
        }
        order.setTotalPrice(totalPrice);
        order.setMetadata(new Metadata(userName));

        orderRepository.save(order);
    }

    @Override
    @Transactional
    public void update(OrderUpdateDTO entity, String userName) {
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
            List<OrderDish> newDishes = mapToOrderDishes(entity.getDishes(), order, userName);
            orderDishService.save(newDishes);
        }

        Metadata md = order.getMetadata();
        md.updateMetadata(userName); // TODO: Get user
        order.setMetadata(md);

        orderRepository.save(order);
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

    private List<OrderDish> mapToOrderDishes(List<DishDTO> dishes, Order order, String userName) {
        return dishes.stream().map(dishDTO -> {
            UUID uuid = UUID.fromString(dishDTO.getDishId());
            Dish dish = dishService.getDishById(uuid).orElseThrow(
                    () -> new EntityNotFoundException("Dish not found")
            );

            OrderDish orderDish = new OrderDish();
            orderDish.setDish(dish);
            orderDish.setQuantity(dishDTO.getQuantity());
            orderDish.setOrder(order);
            orderDish.setMetadata(new Metadata(userName));

            return orderDish;
        }).toList();
    }
}
