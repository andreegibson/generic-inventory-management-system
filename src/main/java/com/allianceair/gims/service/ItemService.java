package com.allianceair.gims.service;

import com.allianceair.gims.model.InventoryItem;
import com.allianceair.gims.repository.InventoryItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ItemService {

    private final InventoryItemRepository inventoryItemRepository;

    public InventoryItem addItem(InventoryItem item) {
        return inventoryItemRepository.save(item);
    }

    public List<InventoryItem> getItems() {
        return inventoryItemRepository.findAll();
    }

    public Optional<InventoryItem> getItemByName(String name) {
        return inventoryItemRepository.findByName(name);
    }

    public List<InventoryItem> getItemsByCategory(String category) {
        return inventoryItemRepository.findByCategory(category);
    }

    public List<InventoryItem> getItemsByType(String type) {
        return inventoryItemRepository.findByType(type);
    }

    public List<InventoryItem> getItemsByBrand(String brand) {
        return inventoryItemRepository.findByBrand(brand);
    }
}
