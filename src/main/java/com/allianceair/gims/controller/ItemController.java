package com.allianceair.gims.controller;

import com.allianceair.gims.dto.InventoryDto;
import com.allianceair.gims.model.InventoryItem;
import com.allianceair.gims.model.ServiceOrder;
import com.allianceair.gims.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
//TODO - Make this better :)
@CrossOrigin("http://localhost:3000")
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public InventoryItem add(@RequestBody InventoryItem item) {
        return itemService.addItem(item);
    }

    @GetMapping
    public List<InventoryDto> getItems() {
        return itemService.getItems();
    }

    @GetMapping("/name/{name}")
    public List<InventoryDto> getByName(@PathVariable String name, @RequestParam(required = false) String category) {
        return category == null ?
                itemService.getItemsByName(name)  :
                itemService.getItemsByNameAndCategory(name, category);
    }

    @GetMapping("/category/{category}")
    public List<InventoryDto> getByCategory(@PathVariable String category) {
        return itemService.getItemsByCategory(category);
    }

    @GetMapping("/type/{type}")
    public List<InventoryDto> getByType(@PathVariable String type) {
        return itemService.getItemsByType(type);
    }

    @GetMapping("/brand/{brand}")
    public List<InventoryDto> getByBrand(@PathVariable String brand) {
        return itemService.getItemsByBrand(brand);
    }

    @PostMapping("/{id}/serviceorders")
    public Optional<InventoryItem> addServiceOrder(@PathVariable String id, @RequestBody ServiceOrder serviceOrder) {
        return itemService.addServiceOrder(id, serviceOrder);
    }

    @GetMapping("/{id}/serviceorders")
    public List<ServiceOrder> getServiceOrders(@PathVariable String id) {
        return itemService.getServiceOrders(id);
    }
}
