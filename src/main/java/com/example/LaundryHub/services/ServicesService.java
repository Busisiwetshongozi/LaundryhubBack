package com.example.LaundryHub.services;

import com.example.LaundryHub.entity.Customer;
import com.example.LaundryHub.entity.Orders;
import com.example.LaundryHub.entity.Payment;
import com.example.LaundryHub.entity.Services;
import com.example.LaundryHub.repository.CustomerRepo;
import com.example.LaundryHub.repository.ServicesRepo;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicesService {
    private final ServicesRepo servicesRepo;
    private final CustomerRepo customerRepo;

    public Services createService(Services services){
        return servicesRepo.save(services);
    }
    public Services getService(Long id){
        return servicesRepo.findById(id).orElse(null);
    }
    public List<Services> getAllServices(){
        return servicesRepo.findAll();

    }



}
