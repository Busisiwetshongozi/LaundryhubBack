package com.example.LaundryHub.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

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
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "service_id")
    private Services services;
    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

}
