package com.example.LaundryHub.services;

import com.example.LaundryHub.entity.Customer;
import com.example.LaundryHub.entity.Orders;
import com.example.LaundryHub.entity.Services;
import com.example.LaundryHub.repository.CustomerRepo;
import com.example.LaundryHub.repository.OrdersRepo;
import com.example.LaundryHub.repository.ServicesRepo;
import jakarta.persistence.criteria.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersService {
    private final OrdersRepo ordersRepo;
    private final CustomerRepo customerRepo;
    private final ServicesRepo servicesRepo;

    public Orders createOrder(Orders orders){
        return ordersRepo.save(orders);
    }

    // Create an order for a specific customer
    public Orders createOrderForCustomer(Long customerId, Orders order) {
        Customer customer = customerRepo.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // Associate the customer with the order
        order.setCustomer(customer);

        // Save the order
        Orders newOrder = ordersRepo.save(order);

        // Associate services with the order
        //if (order.getServices() != null) {
            //for (Services service : order.getServices()) {
                // Optional: Check if the service exists
                //if (servicesRepo.existsById(service.getId())) {
                    //service.getOrders().add(newOrder);
                    //servicesRepo.save(service);
                //}
           // }
        //}

        return newOrder;
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
