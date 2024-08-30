package com.example.LaundryHub.services;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.LaundryHub.entity.Orders;
import com.example.LaundryHub.dto.OrdersDTO;
import com.example.LaundryHub.entity.Payment;
import com.example.LaundryHub.entity.Services;
import com.example.LaundryHub.repository.OrdersRepo;
import com.example.LaundryHub.repository.PaymentRepo;
import com.example.LaundryHub.repository.ServicesRepo;
import com.example.LaundryHub.repository.UserRepo;
import com.example.LaundryHub.user.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


@Service

@RequiredArgsConstructor
public class OrdersService {
    private final OrdersRepo ordersRepo;
    private final UserRepo userRepo;
    private final ServicesRepo servicesRepo;
    private final PaymentRepo paymentRepo;
    private static final Logger logger = (Logger) LoggerFactory.getLogger(OrdersService.class);

    public Orders createOrder(OrdersDTO ordersDTO) {
        Orders order = new Orders();
        order.setDate(LocalDateTime.now());
        order.setStatus(ordersDTO.getStatus());

        // Fetch the current user from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // Get the username from the authentication object

        // Fetch user by username from the repository
        User user = userRepo.findByEmail(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Handle payment if needed
        Payment payment = ordersDTO.getPaymentId() != null
                ? paymentRepo.findById(ordersDTO.getPaymentId()).orElse(null)
                : null;

        order.setUser(user);
        order.setPayment(payment);

        // Fetch services and set to order
        List<Long> serviceIds = ordersDTO.getServiceIds();
        if (serviceIds == null || serviceIds.isEmpty()) {
            throw new IllegalArgumentException("Service IDs must not be null or empty");
        }
        if (serviceIds.stream().anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("Service IDs list must not contain null elements");
        }

        List<Services> services = servicesRepo.findAllById(serviceIds);
        if (services.isEmpty()) {
            throw new IllegalArgumentException("No services found for the provided IDs");
        }
        order.setServices(services);

        // Set the order reference in each service if needed
        for (Services service : services) {
            service.setOrders(order);
        }

        return ordersRepo.save(order);
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
