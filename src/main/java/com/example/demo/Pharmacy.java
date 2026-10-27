
package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Pharmacy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique pharmacy ID

    private String nm;   // Pharmacy name
    private String addr; // Pharmacy address
    private String ph;   // Pharmacy phone number
    private double lat;  // Latitude for GPS distance calculation
    private double lon;  // Longitude for GPS distance calculation

    public Pharmacy() {
    }

    public Pharmacy(String nm, String addr, String ph, double lat, double lon) {
        this.nm = nm;
        this.addr = addr;
        this.ph = ph;
        this.lat = lat;
        this.lon = lon;
    }

    public Long getId() {
        return id;
    }

    public String getNm() {
        return nm;
    }

    public void setNm(String nm) {
        this.nm = nm;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getPh() {
        return ph;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
