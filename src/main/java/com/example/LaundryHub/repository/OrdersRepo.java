package com.example.LaundryHub.repository;

import com.example.LaundryHub.entity.Orders;
import com.example.LaundryHub.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrdersRepo extends JpaRepository<Orders,Long> {
    List<Orders> findByUser(User user);
    Optional<Orders> findTopByUserOrderByDateDesc(User user);
}
