package com.example.LaundryHub.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "services")
public class Services {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String Description;
    double price;
    @ManyToMany(mappedBy = "services")  // Inverse side of the relationship
    private List<Orders> orders;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
