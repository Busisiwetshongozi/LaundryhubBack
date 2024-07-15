package com.example.LaundryHub.services;

import com.example.LaundryHub.entity.Customer;
import com.example.LaundryHub.repository.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
private final  CustomerRepo customerRepo;

public Customer createCustomer(Customer customer){
    return customerRepo.save(customer);
}
public Customer getCustomer(Long id){
    return customerRepo.findById(id).orElse(null);

}

public List<Customer> getAllCustomers(){
    return customerRepo.findAll();
}

    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        Optional<Customer> existingCustomerOptional = customerRepo.findById(id);
        if (existingCustomerOptional.isPresent()) {
            Customer existingCustomer = existingCustomerOptional.get();
            // Update existing customer attributes with values from updatedCustomer
            existingCustomer.setName(updatedCustomer.getName());
            existingCustomer.setEmail(updatedCustomer.getEmail());
            existingCustomer.setAddress(updatedCustomer.getAddress());
            existingCustomer.setNumber(updatedCustomer.getNumber());
            // Add more attributes as needed

            // Save the updated customer object
            return customerRepo.save(existingCustomer);
        } else {
            // Handle case where customer with given id is not found
            return null;
        }
    }

}


