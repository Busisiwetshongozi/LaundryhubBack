package com.example.LaundryHub.repository;

import com.example.LaundryHub.entity.Orders;
import org.hibernate.mapping.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepo extends JpaRepository<Orders,Long> {

}
