package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner load(
            ProductRepo pr,
            PharmacyRepo phr,
            InventoryRepo ir) {

        return args -> {
            if (pr.count() > 0 || phr.count() > 0 || ir.count() > 0) {
                return;
            }

            Product p1 = pr.save(
                    new Product("Paracetamol", "Pain Relief", "ABC Pharma"));
            Product p2 = pr.save(
                    new Product("Cetirizine", "Allergy", "XYZ Pharma"));

            Pharmacy ph1 = phr.save(
                    new Pharmacy("City Medical Store", "Gandhinagar",
                            "9876543210", 23.2156, 72.6369));
            Pharmacy ph2 = phr.save(
                    new Pharmacy("Health Plus Pharmacy", "Gandhinagar",
                            "9876501234", 23.2230, 72.6500));

            ir.save(new Inventory(p1, ph1, 25.0, 50, true));
            ir.save(new Inventory(p1, ph2, 30.0, 20, true));
            ir.save(new Inventory(p2, ph1, 40.0, 15, true));
            ir.save(new Inventory(p2, ph2, 35.0, 0, false));
        };
    }
}
