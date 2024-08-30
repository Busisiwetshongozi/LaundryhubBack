package com.example.LaundryHub.services;

import com.example.LaundryHub.entity.Orders;
import com.example.LaundryHub.entity.Payment;
import com.example.LaundryHub.repository.OrdersRepo;
import com.example.LaundryHub.repository.PaymentRepo;
import com.example.LaundryHub.repository.UserRepo;
import com.example.LaundryHub.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor


public class PaymentService {
    private final PaymentRepo paymentRepo;
    private  final OrdersRepo ordersRepo;
    private final UserRepo userRepo;

    //public Payment createPayment(Payment payment, Long orderId) {
        // Get the currently authenticated user
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //String username = authentication.getName();  // The username should be the email

        // Fetch the user object from the user repository using the email (username)
        //User user = userRepo.findByEmail(username)
               // .orElseThrow(() -> new RuntimeException("User not found"));

        // Fetch the order object from the order repository
        //Orders order = ordersRepo.findById(orderId)
                //.orElseThrow(() -> new RuntimeException("Order not found"));

        // Validate that the order belongs to the user
       // if (!order.getUser().equals(user)) {
            //throw new RuntimeException("Order does not belong to the user");


        // Set the order for the payment
        //payment.setOrder(order);

        // Save the payment
        //return paymentRepo.save(payment);
    //}
        public Payment createPaymentForOrder(Payment payment) {
            // Get the currently authenticated user
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();

            // Fetch the user from the database
            User user = userRepo.findByEmail(username)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            // Fetch the most recent order for the user
            Orders latestOrder = ordersRepo.findTopByUserOrderByDateDesc(user)
                    .orElseThrow(() -> new RuntimeException("No orders found for the user"));

            // Set the user on the payment (optional, based on your schema design)
            payment.setUser(user);

            // Set the order on the payment
            payment.setOrder(latestOrder);

            // Save the payment
            Payment savedPayment = paymentRepo.save(payment);

            // Optionally, update the order to reflect the new payment
            latestOrder.setPayment(savedPayment);
            ordersRepo.save(latestOrder);

            return savedPayment;
        }



    public List<Payment> getAllPayments(){
        return paymentRepo.findAll();

    }

    public List<Payment> getUserPayments() {
        // Get the username from the security context
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        // Fetch the user by username (email)
        User user = userRepo.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Get the list of orders for the user
        return paymentRepo.findByOrderUser(user);
    }

}
