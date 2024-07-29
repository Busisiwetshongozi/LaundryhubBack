package com.example.LaundryHub.controller;

import com.example.LaundryHub.entity.Orders;
import com.example.LaundryHub.entity.Payment;
import com.example.LaundryHub.services.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

   private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/add")
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment){
        Payment createdPayment=paymentService.createPayment(payment);
        return ResponseEntity.ok(createdPayment);

    }
    @GetMapping("/{id}")
    public ResponseEntity<Payment>getPayment(@PathVariable Long id){
        Payment fetchedPayment=paymentService.getPayment(id);
        return ResponseEntity.ok(fetchedPayment);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Payment>> getAllPayments(){
        List<Payment> allPayments= paymentService.getAllPayments();
        return ResponseEntity.ok().body(allPayments);
    }

}
