package com.example.LaundryHub.entity;

import com.example.LaundryHub.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
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
    LocalDateTime date;
    @Column(name="Order Status")
    String status;
    @Column(name="Delivery Address")
    String address;
    String email;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;
    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL, orphanRemoval = true)

    private List<Services> services;
    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

}
