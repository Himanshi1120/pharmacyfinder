
package com.example.demo;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pharmacies")
@CrossOrigin
public class PharmacyController {

    private final PharmacyRepo repo;

    public PharmacyController(PharmacyRepo repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Pharmacy> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Map<String, Object> data) {
        try {
            String nm = String.valueOf(data.getOrDefault("nm", "")).trim();
            String addr = String.valueOf(data.getOrDefault("addr", "")).trim();
            String ph = String.valueOf(data.getOrDefault("ph", "")).trim();

            if (nm.isBlank() || addr.isBlank()) {
                return ResponseEntity.badRequest()
                        .body(Map.of("message", "Pharmacy name and address are required"));
            }

            Pharmacy p = new Pharmacy();
            p.setNm(nm);
            p.setAddr(addr);
            p.setPh(ph);
            p.setLat(Double.parseDouble(String.valueOf(data.get("lat"))));
            p.setLon(Double.parseDouble(String.valueOf(data.get("lon"))));

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(repo.save(p));

        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "Enter valid latitude and longitude"));
        }
    }
}
