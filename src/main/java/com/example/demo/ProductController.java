
package com.example.demo;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@CrossOrigin
public class ProductController {

    private final ProductRepo repo;

    public ProductController(ProductRepo repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Product> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Map<String, Object> data) {
        try {
            String nm = data.get("nm") == null ? "" : data.get("nm").toString().trim();
            String cat = data.get("cat") == null ? "" : data.get("cat").toString().trim();
            String mk = data.get("mk") == null ? "" : data.get("mk").toString().trim();

            if (nm.isBlank()) {
                return ResponseEntity.badRequest()
                        .body(Map.of("message", "Medicine name is required"));
            }

            Product p = new Product();
            p.setNm(nm);
            p.setCat(cat);
            p.setMk(mk);

            Product saved = repo.save(p);

            return ResponseEntity.status(HttpStatus.CREATED).body(saved);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Could not save product",
                                 "error", e.getMessage() == null ? "Unknown error" : e.getMessage()));
        }
    }
}
