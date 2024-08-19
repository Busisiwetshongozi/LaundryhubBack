package com.example.LaundryHub.controller;

import com.example.LaundryHub.entity.Payment;
import com.example.LaundryHub.repository.OrdersRepo;
import com.example.LaundryHub.repository.PaymentRepo;
import com.example.LaundryHub.repository.UserRepo;
import com.example.LaundryHub.services.PaymentService;
import com.example.LaundryHub.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.LaundryHub.entity.Orders;
import com.example.LaundryHub.entity.Payment;
import com.example.LaundryHub.services.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import java.util.List;
@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class PaymentController {

    private final PaymentService paymentService;

 public PaymentController (PaymentService paymentService){
     this.paymentService=paymentService;

 }

    @PostMapping("/add")
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) {
        try {
            Payment createdPayment = paymentService.createPaymentForOrder(payment);
            return ResponseEntity.ok(createdPayment);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @GetMapping("/all")
        public ResponseEntity<List<Payment>> getAllPayments(){
            List<Payment> allPayments= paymentService.getAllPayments();
            return ResponseEntity.ok().body(allPayments);
        }

    }

