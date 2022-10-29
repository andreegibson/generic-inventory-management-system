package com.allianceair.gims.service;

import com.allianceair.gims.dto.InventoryDto;
import com.allianceair.gims.model.InventoryItem;
import com.allianceair.gims.model.ServiceOrder;
import com.allianceair.gims.repository.CategoryRepository;
import com.allianceair.gims.repository.InventoryItemRepository;
import com.allianceair.gims.repository.TypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ItemService {

    private final InventoryItemRepository inventoryItemRepository;
    private final CategoryRepository categoryRepository;
    private final TypeRepository typeRepository;

    public InventoryItem addItem(InventoryItem item) {
        return inventoryItemRepository.save(item);
    }

    public List<InventoryDto> getItems() {

        //NOTE: I dunno I do not love this, but I do not hate it. But this might be me mapping my dinosaur technology solutions to the new age
        return inventoryItemRepository.findAll().stream()
                .map(item -> InventoryDto.builder()
                        .id(item.getId())
                        .name(item.getName())
                        .category(categoryRepository.findById(item.getCategory()).get().getName())
                        .type(typeRepository.findById(item.getType()).get().getName())
                        .brand(item.getBrand())
                        .description(item.getDescription())
                        .imageUrl(item.getImageUrl())
                        .serviceOrders(item.getServiceOrders())
                        .build())
                .collect(Collectors.toList());
    }

    public List<InventoryItem> getItemsByName(String name) {
        return inventoryItemRepository.findByNameStartsWithIgnoreCase(name);
    }
    public List<InventoryItem> getItemsByNameAndCategory(String name, String category) {
        return inventoryItemRepository.findAllByNameAndCategory(name, category);
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