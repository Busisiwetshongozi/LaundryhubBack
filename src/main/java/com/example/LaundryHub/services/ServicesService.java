package com.example.LaundryHub.services;

import com.example.LaundryHub.entity.Services;
import com.example.LaundryHub.repository.ServicesRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicesService {
    private final ServicesRepo servicesRepo;


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
