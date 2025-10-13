package com.fabiomijango.gestor_restaurante.service.serviceImpl;

import com.fabiomijango.gestor_restaurante.entity.OrderDish;
import com.fabiomijango.gestor_restaurante.entity.dto.orderdish.OrderDishSaveDTO;
import com.fabiomijango.gestor_restaurante.entity.dto.orderdish.OrderDishUpdateDTO;
import com.fabiomijango.gestor_restaurante.repository.iOrderDishRepository;
import com.fabiomijango.gestor_restaurante.service.iOrderDishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderDishServiceImpl implements iOrderDishService {

    @Autowired
    private iOrderDishRepository orderDishRepository;

    @Override
    public void deleteAllOrderDishesByOrderId(UUID orderId) {
        orderDishRepository.deleteAllByOrder_Id(orderId);
    }

    @Override
    public List<OrderDish> findAllByOrderId(UUID orderId) {
        return orderDishRepository.findAllByOrder_Id(orderId);
    }


    @Override
    public void save(OrderDishSaveDTO entity) {
        // Not needed?
        throw  new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(OrderDishUpdateDTO entity) {
        // Not needed?
        throw  new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteById(UUID uuid) {
        orderDishRepository.deleteById(uuid);
    }

    @Override
    public Optional<OrderDish> findById(UUID uuid) {
        return orderDishRepository.findById(uuid);
    }

    @Override
    public List<OrderDish> findAll() {
        return orderDishRepository.findAll();
    }
}
