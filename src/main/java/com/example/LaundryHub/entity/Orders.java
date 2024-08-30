package com.example.LaundryHub.entity;

import com.example.LaundryHub.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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


    @Column(name = "Date of Order")
    LocalDateTime date = LocalDateTime.now();
    @Column(name="Order Status")
    String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Services> services;

    @OneToOne
    @JoinColumn(name = "payment_id")
    @JsonManagedReference
    private Payment payment;
    public Orders() {
        // Optionally, initialize date here as well
        this.date = LocalDateTime.now();
    }
    // Method to get payment amount
    public Double getPaymentAmount() {
        return payment != null ? payment.getAmount() : null;
    }
    public Long getPaymentId() {
        return payment != null ? payment.getId() : null;
    }

}
