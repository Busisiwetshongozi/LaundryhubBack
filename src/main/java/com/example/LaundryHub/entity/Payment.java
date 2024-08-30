package com.example.LaundryHub.entity;

import com.example.LaundryHub.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JsonBackReference
    private Orders order;
    @ManyToOne
    @JoinColumn(name = "user_id") // Foreign key column
    private User user;

}
