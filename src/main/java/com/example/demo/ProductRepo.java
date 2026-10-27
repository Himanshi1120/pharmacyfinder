
package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {

    // Finds products whose names contain the search text
    List<Product> findByNmContainingIgnoreCase(String nm);
}
