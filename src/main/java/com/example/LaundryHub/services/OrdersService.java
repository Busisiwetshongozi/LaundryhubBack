package com.example.LaundryHub.services;

import com.example.LaundryHub.entity.Orders;
import com.example.LaundryHub.entity.Services;
import com.example.LaundryHub.repository.OrdersRepo;
import com.example.LaundryHub.repository.ServicesRepo;
import com.example.LaundryHub.repository.UserRepo;
import com.example.LaundryHub.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersService {
    private final OrdersRepo ordersRepo;
    private final UserRepo userRepo;
    private final ServicesRepo servicesRepo;


    public Orders createOrder(Orders order) {
        // Get the currently authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();  // The username should be the email

        // Fetch the user object from the user repository using the email (username)
        User user = userRepo.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Associate the user with the order
        order.setUser(user);

        // Associate each service with the order
        if (order.getServices() != null) {
            for (Services service : order.getServices()) {
                // Set the current order to each service
                service.setOrders(order);
            }
        }

        // Save the order (which will also save associated services and payment due to cascade settings)
        Orders newOrder = ordersRepo.save(order);

        return newOrder;
    }

    public List<Orders> getUserOrders() {
        // Get the username from the security context
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        // Fetch the user by username (email)
        User user = userRepo.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Get the list of orders for the user
        return ordersRepo.findByUser(user);
    }
public  void deleteOrders( Long id){
       this.ordersRepo.deleteById(id);
}
}
