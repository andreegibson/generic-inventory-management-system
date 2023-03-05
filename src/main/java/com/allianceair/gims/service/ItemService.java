package com.allianceair.gims.service;

import com.allianceair.gims.model.ChargeOffCodes;
import com.allianceair.gims.model.InventoryItem;
import com.allianceair.gims.model.InventoryStatus;
import com.allianceair.gims.model.ServiceOrder;
import com.allianceair.gims.model.query.InventorySummary;
import com.allianceair.gims.repository.InventoryItemRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        return inventoryItemRepository.findByNameStartsWithIgnoreCaseAndDateDeletedIsNull(name);
    }
    public List<InventoryItem> getItemsByNameAndCategory(String name, String category) {
        return inventoryItemRepository.findAllByNameAndCategoryStartsWithIgnoreCase(name, category);
    }

    public List<InventoryItem> getItemsByCategory(String category) {
        return inventoryItemRepository.findByCategoryStartsWithIgnoreCaseAndDateDeletedIsNull(category);
    }

    public List<InventoryItem> getItemsByType(String type) {
        return inventoryItemRepository.findByTypeStartsWithIgnoreCaseAndDateDeletedIsNull(type);
    }

    public List<InventoryItem> getItemsByBrand(String brand) {
        return inventoryItemRepository.findByBrandStartsWithIgnoreCaseAndDateDeletedIsNull(brand);
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

    public Long countOpenServiceOrders() {
        return inventoryItemRepository.countOpenServiceOrders();
    }

    public void chargeOffItem(String inventoryId, String reason) {
        inventoryItemRepository.findById(inventoryId).ifPresent(item -> {
            item.setInventoryStatus(InventoryStatus.Deleted);
            item.setChargeOfReason(reason);
            item.setDateDeleted(LocalDateTime.now());

            inventoryItemRepository.save(item);
        });
    }

    public InventoryItem findById(String id) {
        return inventoryItemRepository.findById(id).orElseThrow();
    }

    public List<String> getChargeOffCodes() {
        return Arrays.asList(ChargeOffCodes.values()).stream()
                .filter(code -> !code.equals(ChargeOffCodes.Sold))
                .map(code -> code.toString())
                .collect(Collectors.toList());
    }
}