package com.example.LaundryHub.controller;

import com.example.LaundryHub.entity.Payment;
import com.example.LaundryHub.repository.OrdersRepo;
import com.example.LaundryHub.repository.PaymentRepo;
import com.example.LaundryHub.repository.UserRepo;
import com.example.LaundryHub.services.PaymentService;
import com.example.LaundryHub.services.UserService;
import com.example.LaundryHub.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final UserService userService;

 public PaymentController (PaymentService paymentService,UserService userService){
     this.paymentService=paymentService;
     this.userService=userService;

 }
    @PostMapping("/add")
    public ResponseEntity<Payment> createPaymentForOrder(@RequestBody Payment payment) {
        try {
            Payment createdPayment = paymentService.createPaymentForOrder(payment);
            return new ResponseEntity<>(createdPayment, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user")
    public ResponseEntity<List<Payment>> getUserPayments() {
        try {
            List  <Payment> payments = paymentService.getUserPayments();
            return new ResponseEntity<>(payments, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/all")
        public ResponseEntity<List<Payment>> getAllPayments(){
            List<Payment> allPayments= paymentService.getAllPayments();
            return ResponseEntity.ok().body(allPayments);
        }

    }

