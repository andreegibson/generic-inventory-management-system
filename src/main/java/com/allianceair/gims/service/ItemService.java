package com.allianceair.gims.service;

import com.allianceair.gims.model.InventoryItem;
import com.allianceair.gims.model.ServiceOrder;
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

    public List<InventoryItem> getItemsByName(String name) {
        return inventoryItemRepository.findByNameStartsWith(name);
    }
    public List<InventoryItem> getItemsByNameAndCategory(String name, String category) {
        return inventoryItemRepository.findAllByNameAndCategory(name, category);
    }

    public List<InventoryItem> getItemsByCategory(String category) {
        return inventoryItemRepository.findByCategoryStartsWith(category);
    }

    public List<InventoryItem> getItemsByType(String type) {
        return inventoryItemRepository.findByTypeStartsWith(type);
    }

    public List<InventoryItem> getItemsByBrand(String brand) {
        return inventoryItemRepository.findByBrandStartsWith(brand);
    }

    public List<ServiceOrder> getServiceOrders(String id) {
        return inventoryItemRepository.findById(id).map(InventoryItem::getServiceOrders).orElse(null);
    }

    public Optional<InventoryItem> addServiceOrder(String id, ServiceOrder serviceOrder) {
        Optional<InventoryItem> result = inventoryItemRepository.findById(id);

        //NOTE: Probably a better way to do this, will clean up later
        result.ifPresent(item -> {
            item.getServiceOrders().add(serviceOrder);

            inventoryItemRepository.save(item);
        });

        return result;
    }
}