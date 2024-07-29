package com.example.LaundryHub.services;

import com.example.LaundryHub.entity.Orders;
import com.example.LaundryHub.entity.Payment;
import com.example.LaundryHub.repository.PaymentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor


public class PaymentService {
    private final PaymentRepo paymentRepo;

    public Payment createPayment(Payment payment){
        return paymentRepo.save(payment);
    }

    public Payment getPayment(Long id){
        return paymentRepo.findById(id).orElse(null);
    }

    public List<Payment> getAllPayments(){
        return paymentRepo.findAll();

    }
}
