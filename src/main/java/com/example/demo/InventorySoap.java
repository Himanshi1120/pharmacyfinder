package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventorySoap {

    private final InventoryRepo repo;

    public InventorySoap(InventoryRepo repo) {
        this.repo = repo;
    }

    // Finds inventory records for a product by name
    public List<Inventory> find(String nm) {
        return repo.findAll().stream()
                .filter(i -> i.getProduct() != null
                        && i.getProduct().getNm() != null
                        && i.getProduct().getNm().equalsIgnoreCase(nm))
                .toList();
    }
}
