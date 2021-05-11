package com.allianceair.gims.controller;

import com.allianceair.gims.model.InventoryItem;
import com.allianceair.gims.service.ItemCreatorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/items")
public class ItemController {

    private ItemCreatorService itemCreatorService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public InventoryItem add(@RequestBody InventoryItem item) {
        return itemCreatorService.addItem(item);
    }

    @GetMapping
    public List<InventoryItem> getItems() {
        return itemCreatorService.getItems();
    }

    @GetMapping("/name/{name}")
    public Optional<InventoryItem> getByName(@PathVariable String name) {
        return itemCreatorService.getItemByName(name);
    }
}
