package com.example.LaundryHub.controller;

import com.example.LaundryHub.entity.Customer;
import com.example.LaundryHub.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {


private final CustomerService customerService;

@PostMapping("/add")
public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
    Customer createdCustomer= customerService.createCustomer(customer);
    return ResponseEntity.ok(createdCustomer);
}
@GetMapping("/{id}")
public ResponseEntity<Customer> getCustomer(@PathVariable Long id){
  Customer fetchedCustomer=customerService.getCustomer(id) ;
          return ResponseEntity.ok(fetchedCustomer);
}

@GetMapping("/all")
public ResponseEntity<List<Customer>>getAllCustomers(){
    List<Customer> fetchedCustomers=customerService.getAllCustomers();
    return ResponseEntity.ok().body(fetchedCustomers);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
        Customer updatedCustomerEntity = customerService.updateCustomer(id, updatedCustomer);

        if (updatedCustomerEntity != null) {
            return ResponseEntity.ok(updatedCustomerEntity);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
