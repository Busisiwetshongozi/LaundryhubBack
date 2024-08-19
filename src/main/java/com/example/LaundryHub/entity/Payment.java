package com.example.LaundryHub.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "payments")
public class Payment {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Double amount;
    Date date;
    @OneToOne(mappedBy = "payment")
    private Orders order;
}
