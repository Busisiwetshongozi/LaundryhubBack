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
    String customerName;
    @Column(name = "Date of Order")
    Date date;
    @Column(name="Order Status")
    String status;
    @Column(name="Price")
    Double price;
    @Column(name="Delivery Address")
    String deliveryAddress;

}
