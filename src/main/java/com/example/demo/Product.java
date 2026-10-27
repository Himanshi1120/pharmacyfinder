
package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Automatically generated product ID

    private String nm;  // Product name
    private String cat; // Product category
    private String mk;  // Manufacturer name

    public Product() {
    }

    // Creates a product with its name, category, and manufacturer
    public Product(String nm, String cat, String mk) {
        this.nm = nm;
        this.cat = cat;
        this.mk = mk;
    }

    // Getters return values; setters update values
    public Long getId() {
        return id;
    }

    public String getNm() {
        return nm;
    }

    public void setNm(String nm) {
        this.nm = nm;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getMk() {
        return mk;
    }

    public void setMk(String mk) {
        this.mk = mk;
    }
}
