package com.allianceair.gims.service;

import com.allianceair.gims.model.InventoryItem;
import com.allianceair.gims.model.ServiceOrder;
import com.allianceair.gims.model.query.InventorySummary;
import com.allianceair.gims.repository.InventoryItemRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class ItemService {

    private final InventoryItemRepository inventoryItemRepository;

    public InventoryItem addItem(InventoryItem item) {
        item.setDateAdded(LocalDateTime.now());

        return inventoryItemRepository.save(item);
    }

    public List<InventoryItem> getItems() {
        return inventoryItemRepository.findAll();
    }

    public List<InventoryItem> getItemsByName(String name) {
        return inventoryItemRepository.findByNameStartsWithIgnoreCase(name);
    }
    public List<InventoryItem> getItemsByNameAndCategory(String name, String category) {
        return inventoryItemRepository.findAllByNameAndCategoryStartsWithIgnoreCase(name, category);
    }

    public List<InventoryItem> getItemsByCategory(String category) {
        return inventoryItemRepository.findByCategoryStartsWithIgnoreCase(category);
    }

    public List<InventoryItem> getItemsByType(String type) {
        return inventoryItemRepository.findByTypeStartsWithIgnoreCase(type);
    }

    public List<InventoryItem> getItemsByBrand(String brand) {
        return inventoryItemRepository.findByBrandStartsWithIgnoreCase(brand);
    }

    public List<ServiceOrder> getServiceOrders(String id) {
        return inventoryItemRepository.findById(id).map(InventoryItem::getServiceOrders).orElse(null);
    }

    public List<InventorySummary> countInventoryByType() {
        return inventoryItemRepository.countInventoryByType();
    }

    public Optional<InventoryItem> addServiceOrder(String id, ServiceOrder serviceOrder) {
        Optional<InventoryItem> result = inventoryItemRepository.findById(id);

        //NOTE: Probably a better way to do this, will clean up later
        result.ifPresent(item -> {
            if(item.getServiceOrders() == null) {
                item.setServiceOrders(new ArrayList<>() {
                });
            }
            item.getServiceOrders().add(serviceOrder);

            inventoryItemRepository.save(item);
        });

        return result;
    }
}