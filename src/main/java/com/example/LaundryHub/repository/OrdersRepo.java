package com.example.LaundryHub.repository;

import com.example.LaundryHub.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepo extends JpaRepository<Orders,Long> {
    List<Orders> findByCustomerId(Long customerId);
}
