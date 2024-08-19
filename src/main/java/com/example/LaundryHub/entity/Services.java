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

    @ManyToOne
    @JoinColumn(name = "order_id") // Foreign key in Services table
    private Orders orders;


}
