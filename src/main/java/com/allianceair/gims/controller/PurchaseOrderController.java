package com.allianceair.gims.controller;

import com.allianceair.gims.model.PurchaseOrder;
import com.allianceair.gims.service.PurchaseOrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
//TODO - Make this better :)
@CrossOrigin("http://localhost:3000")
@RequestMapping("/purchaseorders")
@Slf4j
public class PurchaseOrderController {
    @Autowired
    private final PurchaseOrderService purchaseOrderService;

    @GetMapping
    public List<PurchaseOrder> findAllPurchaseOrders() {
        return purchaseOrderService.findAllPurchaseOrders();
    }

    @PostMapping
    public PurchaseOrder addPurchaseOrder(@RequestBody PurchaseOrder purchaseOrder) {
        return purchaseOrderService.addPurchaseOrder(purchaseOrder);
    }

    @GetMapping("/{id}")
    public PurchaseOrder findPurchaseOrder(@PathVariable String id) {
        return purchaseOrderService.findById(id);
    }

    @PutMapping()
    public PurchaseOrder updatePurchaseOrder(@RequestBody PurchaseOrder purchaseOrder) {
        return purchaseOrderService.update(purchaseOrder);
    }
}
