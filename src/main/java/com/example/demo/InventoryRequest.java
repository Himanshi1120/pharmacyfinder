
package com.example.demo;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "InventoryRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class InventoryRequest {

    private String nm; // Product name requested through SOAP

    public InventoryRequest() {
    }

    public InventoryRequest(String nm) {
        this.nm = nm;
    }

    public String getNm() {
        return nm;
    }

    public void setNm(String nm) {
        this.nm = nm;
    }
}
