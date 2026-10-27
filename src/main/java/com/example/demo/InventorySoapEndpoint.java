package com.example.demo;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class InventorySoapEndpoint {

    private static final String NS = "http://example.com/pharmacy";

    private final InventorySoap svc;

    public InventorySoapEndpoint(InventorySoap svc) {
        this.svc = svc;
    }

    // Receives an XML request and returns inventory information as XML
    @PayloadRoot(namespace = NS, localPart = "InventoryRequest")
    @ResponsePayload
    public InventoryResponse find(@RequestPayload InventoryRequest req) {
        List<Inventory> list = svc.find(req.getNm());

        int qty = list.stream()
                .filter(i -> i.isAvl())
                .mapToInt(Inventory::getQty)
                .sum();

        return new InventoryResponse(
                req.getNm(),
                qty,
                qty > 0
        );
    }
}
