package com.example.demo;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "InventoryResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class InventoryResponse {

    private String nm; // Product name
    private int qty;   // Total available quantity
    private boolean avl; // Whether stock is available

    public InventoryResponse() {
    }

    public InventoryResponse(String nm, int qty, boolean avl) {
        this.nm = nm;
        this.qty = qty;
        this.avl = avl;
    }

    public String getNm() {
        return nm;
    }

    public void setNm(String nm) {
        this.nm = nm;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public boolean isAvl() {
        return avl;
    }

    public void setAvl(boolean avl) {
        this.avl = avl;
    }
}
