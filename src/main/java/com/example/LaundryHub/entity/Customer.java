package com.example.LaundryHub.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Customers")

public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "Customer Name")
    String name;
    @Column(name = "Customer email address")
    String email;
    @Column(name = "Customer Address")
    String address;
    @Column(name = "Customer Cell Number")
    Integer number;


}
