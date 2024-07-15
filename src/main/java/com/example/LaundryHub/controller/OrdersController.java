package com.example.LaundryHub.controller;

import com.example.LaundryHub.entity.Orders;
import com.example.LaundryHub.services.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrdersController {
    private final OrdersService ordersService;

@PostMapping("/add")
    public ResponseEntity<Orders> createOrder( @RequestBody Orders orders){
      Orders createdOrder=ordersService.createOrder(orders);
      return ResponseEntity.ok(createdOrder);

    }
@GetMapping("/{id}")
    public ResponseEntity<Orders>getOrder(@PathVariable Long id){
    Orders fetchedOrder=ordersService.getOrder(id);
    return ResponseEntity.ok(fetchedOrder);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Orders>> getAllOrders(){
    List<Orders> allOrders= ordersService.getAllOrders();
    return ResponseEntity.ok().body(allOrders);
    }

}
