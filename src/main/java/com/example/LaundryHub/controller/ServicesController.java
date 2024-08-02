package com.example.LaundryHub.controller;

import com.example.LaundryHub.entity.Orders;
import com.example.LaundryHub.entity.Services;
import com.example.LaundryHub.services.ServicesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/services")
@CrossOrigin(origins = "http://localhost:4200")
public class ServicesController {
    private final ServicesService servicesService ;

    @PostMapping("/add")
    public ResponseEntity<Services> createService(@RequestBody Services services) {
        Services createdService = servicesService.createService(services);
        return ResponseEntity.ok(createdService);

    }



    @GetMapping("/{id}")
    public ResponseEntity<Services>getServices(@PathVariable Long id){
        Services fetchedService=servicesService.getService(id);
        return ResponseEntity.ok(fetchedService);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Services>> getAllServices(){
        List<Services> allServices= servicesService.getAllServices();
        return ResponseEntity.ok().body(allServices);
    }
}
