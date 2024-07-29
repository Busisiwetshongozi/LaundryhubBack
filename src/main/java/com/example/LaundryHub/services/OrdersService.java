package com.example.LaundryHub.services;

import com.example.LaundryHub.entity.Customer;
import com.example.LaundryHub.entity.Orders;
import com.example.LaundryHub.repository.CustomerRepo;
import com.example.LaundryHub.repository.OrdersRepo;
import jakarta.persistence.criteria.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersService {
    private final OrdersRepo ordersRepo;
    private final CustomerRepo customerRepo;

    public Orders createOrder(Orders orders){
        return ordersRepo.save(orders);
    }
    public Orders createOrderForCustomer(Long customerId, Orders order) {
        Customer customer = customerRepo.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
        order.setCustomer(customer);
        return ordersRepo.save(order);
    }
    public List<Orders> getOrdersByCustomerId(Long customerId) {
        return ordersRepo.findByCustomerId(customerId);
    }

    public Orders getOrder(Long id){
        return ordersRepo.findById(id).orElse(null);
    }

    public List<Orders>getAllOrders(){
        return ordersRepo.findAll();

    }

}
