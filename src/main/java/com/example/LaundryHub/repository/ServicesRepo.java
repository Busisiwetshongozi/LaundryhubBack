package com.example.LaundryHub.repository;

import com.example.LaundryHub.entity.Services;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServicesRepo extends JpaRepository<Services,Long> {
    List<Services> findByCustomerId(Long customerId);
}
