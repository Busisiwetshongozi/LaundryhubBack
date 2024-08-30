package com.example.LaundryHub.controller;

import com.example.LaundryHub.entity.Orders;
import com.example.LaundryHub.dto.OrdersDTO;
import com.example.LaundryHub.services.OrdersService;
import com.example.LaundryHub.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class OrdersController {
    private final OrdersService ordersService;
    private final UserService userService;


    @PostMapping("/add")
    public ResponseEntity<Orders> createOrder(@RequestBody OrdersDTO ordersDTO) {
        try {
            Orders createdOrder = ordersService.createOrder(ordersDTO);
            return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            // Log the error and return a bad request status
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            // Log the error and return an internal server error status
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


   // @PostMapping("/add/{customerId}")
   // public ResponseEntity<Orders> addOrder(@PathVariable Long customerId, @RequestBody Orders order) {
       // Orders newOrder = ordersService.addOrder(customerId, order);
       // return ResponseEntity.ok(newOrder);
    //}



    @GetMapping("user")
    public ResponseEntity<List<Orders>> getUserOrders() {
        try {
          List  <Orders> orders = ordersService.getUserOrders();
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Orders>> getAllOrders(){
    List<Orders> allOrders= ordersService.getUserOrders();
    return ResponseEntity.ok().body(allOrders);
    }

    @DeleteMapping("/{id}")
    public void deleteOrders(@PathVariable Long id){
        this.ordersService.deleteOrders(id);

    }

}
