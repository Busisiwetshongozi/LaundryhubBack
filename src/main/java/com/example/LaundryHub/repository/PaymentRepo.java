package com.example.LaundryHub.repository;

import com.example.LaundryHub.entity.Payment;
import com.example.LaundryHub.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepo extends JpaRepository<Payment,Long> {
    List<Payment> findByOrderUser(User user);
}
