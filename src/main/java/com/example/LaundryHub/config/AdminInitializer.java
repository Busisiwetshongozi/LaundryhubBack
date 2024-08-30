package com.example.LaundryHub.config;

import com.example.LaundryHub.entity.Services;
import com.example.LaundryHub.repository.OrdersRepo;
import com.example.LaundryHub.repository.ServicesRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdminInitializer {

    @Autowired
    private OrdersRepo ordersRepo;

    @Autowired
    private ServicesRepo servicesRepo;

    @PostConstruct
    public void init() {
        // Clean existing data
        if (servicesRepo.count() == 0) {
        servicesRepo.deleteAll();
        ordersRepo.deleteAll();

        // Create and save default services
        Services defaultService1 = new Services();
        defaultService1.setName("Wash Only");
        defaultService1.setDescription("We Wash Your Clothes And Neatly Fold Them");
        defaultService1.setPrice(50.00);

        Services defaultService2 = new Services();
        defaultService2.setName("Wash And Iron");
        defaultService2.setDescription("We Wash,Iron And neatly Fold Your Clothes");
        defaultService2.setPrice(70.00);

        Services defaultService3 = new Services();
        defaultService3.setName("Iron Only");
        defaultService3.setDescription("We Wash Your Already Clean Clothes");
        defaultService3.setPrice(40.00);

        Services defaultService4 = new Services();
        defaultService4.setName("Blankets Wash");
        defaultService4.setDescription("We Wash  your 3 Blankets ");
        defaultService4.setPrice(70.00);

        servicesRepo.save(defaultService1);
        servicesRepo.save(defaultService2);
        servicesRepo.save(defaultService3);
        servicesRepo.save(defaultService4);



}}}
