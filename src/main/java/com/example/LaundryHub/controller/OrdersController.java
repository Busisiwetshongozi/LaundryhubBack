package com.example.LaundryHub.controller;

import com.example.LaundryHub.entity.Orders;
import com.example.LaundryHub.services.OrdersService;
import com.example.LaundryHub.services.UserService;
import com.example.LaundryHub.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public ResponseEntity<Orders> createOrder(@RequestBody Orders orders) {
        // Get the currently authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // Or however you identify your users

        // Fetch the user object from the user service or repository
        User user = userService.findByEmail(username);

        // Set the user to the order
        orders.setUser(user);

        // Save the order with the associated user
        Orders createdOrder = ordersService.createOrder(orders);
        return ResponseEntity.ok(createdOrder);
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

}
