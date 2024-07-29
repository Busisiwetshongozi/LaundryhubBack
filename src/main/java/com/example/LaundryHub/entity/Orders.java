package com.example.LaundryHub.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name="orders")
public class Orders {
    @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id ;

    @Column(name = "Customer Name")
    String name;
    @Column(name = "Date of Order")
    Date date;
    @Column(name="Order Status")
    String status;
    @Column(name="Delivery Address")
    String address;
    String email;
    @ManyToOne
    @JoinColumn(name = "customer_id")  // Foreign key column in orders table
    private Customer customer;
}
