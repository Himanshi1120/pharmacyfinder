
package com.example.demo;

import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin
public class InventoryController {

    private final InventoryRepo invRepo;
    private final ProductRepo prodRepo;
    private final PharmacyRepo pharmRepo;

    public InventoryController(
            InventoryRepo invRepo,
            ProductRepo prodRepo,
            PharmacyRepo pharmRepo) {
        this.invRepo = invRepo;
        this.prodRepo = prodRepo;
        this.pharmRepo = pharmRepo;
    }

    @GetMapping
    public List<Map<String, Object>> getAll() {
        return makeResults(invRepo.findAll());
    }

    @GetMapping("/compare")
    public List<Map<String, Object>> compare(
            @RequestParam(required = false) String nm,
            @RequestParam(required = false) String nms) {

        String names = nms != null && !nms.isBlank() ? nms : nm;

        List<Inventory> all = invRepo.findAll();

        if (names == null || names.isBlank()) {
            return makeResults(all);
        }

        Set<String> wanted = new HashSet<>();
        for (String name : names.split(",")) {
            if (!name.isBlank()) {
                wanted.add(name.trim().toLowerCase(Locale.ROOT));
            }
        }

        List<Inventory> found = all.stream()
                .filter(i -> i.getProduct() != null)
                .filter(i -> wanted.contains(
                        i.getProduct().getNm().trim().toLowerCase(Locale.ROOT)))
                .toList();

        return makeResults(found);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Map<String, Object> data) {
        try {
            Long productId = Long.parseLong(String.valueOf(data.get("productId")));
            Long pharmacyId = Long.parseLong(String.valueOf(data.get("pharmacyId")));
            double price = Double.parseDouble(String.valueOf(data.get("pr")));
            int qty = Integer.parseInt(String.valueOf(data.get("qty")));
            boolean avl = Boolean.parseBoolean(String.valueOf(data.get("avl")));

            if (price < 0 || qty < 0) {
                return ResponseEntity.badRequest()
                        .body(Map.of("message", "Price and quantity cannot be negative"));
            }

            Product product = prodRepo.findById(productId).orElse(null);
            Pharmacy pharmacy = pharmRepo.findById(pharmacyId).orElse(null);

            if (product == null || pharmacy == null) {
                return ResponseEntity.badRequest()
                        .body(Map.of("message", "Select a valid medicine and pharmacy"));
            }

            Inventory inv = new Inventory();
            inv.setProduct(product);
            inv.setPharmacy(pharmacy);
            inv.setPr(price);
            inv.setQty(qty);
            inv.setAvl(avl && qty > 0);

            Inventory saved = invRepo.save(inv);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(makeResults(List.of(saved)).get(0));

        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "Invalid inventory details"));
        }
    }

    private List<Map<String, Object>> makeResults(List<Inventory> items) {
        List<Map<String, Object>> results = new ArrayList<>();

        for (Inventory i : items) {
            Map<String, Object> row = new LinkedHashMap<>();

            row.put("id", i.getId());
            row.put("productId", i.getProduct().getId());
            row.put("medicine", i.getProduct().getNm());
            row.put("category", i.getProduct().getCat());
            row.put("manufacturer", i.getProduct().getMk());
            row.put("pharmacyId", i.getPharmacy().getId());
            row.put("pharmacy", i.getPharmacy().getNm());
            row.put("address", i.getPharmacy().getAddr());
            row.put("phone", i.getPharmacy().getPh());
            row.put("lat", i.getPharmacy().getLat());
            row.put("lon", i.getPharmacy().getLon());
            row.put("price", i.getPr());
            row.put("quantity", i.getQty());
            row.put("available", i.isAvl());

            results.add(row);
        }

        return results;
    }
}
