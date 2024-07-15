package com.example.LaundryHub.services;

import com.example.LaundryHub.entity.Orders;
import com.example.LaundryHub.repository.OrdersRepo;
import jakarta.persistence.criteria.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersService {
    private final OrdersRepo ordersRepo;

    public Orders createOrder(Orders orders){
        return ordersRepo.save(orders);
    }

    public Orders getOrder(Long id){
        return ordersRepo.findById(id).orElse(null);
    }

    public List<Orders>getAllOrders(){
        return ordersRepo.findAll();

    }

}
