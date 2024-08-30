package com.example.LaundryHub.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
public class OrdersDTO {
    private String status; // Status of the order
    private List<Long> serviceIds; // List of service IDs
    private Long paymentId;
}
