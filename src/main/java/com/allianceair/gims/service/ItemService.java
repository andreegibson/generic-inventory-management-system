package com.allianceair.gims.service;

import com.allianceair.gims.dto.InventoryDto;
import com.allianceair.gims.model.Category;
import com.allianceair.gims.model.InventoryItem;
import com.allianceair.gims.model.ServiceOrder;
import com.allianceair.gims.model.Type;
import com.allianceair.gims.repository.CategoryRepository;
import com.allianceair.gims.repository.InventoryItemRepository;
import com.allianceair.gims.repository.TypeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
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
        return mapToDtos(inventoryItemRepository.findAll());
    }

    public List<InventoryDto> getItemsByName(String name) {
        return mapToDtos(inventoryItemRepository.findByNameStartsWith(name));
    }
    public List<InventoryDto> getItemsByNameAndCategory(String name, String category) {
        return mapToDtos(inventoryItemRepository.findAllByNameAndCategory(name, category));
    }

    public List<InventoryDto> getItemsByCategory(String categoryName) {
        List<Category> categories = categoryRepository.findByNameStartsWith(categoryName);
        List<InventoryItem> items = new ArrayList<>();

        log.debug("found categories {}", categories);

        //NOTE: ok so I did this part second and I am liking it less and am thinking to store the category name in the inventory table even though it is normalized
        categories.forEach(category -> items.addAll(inventoryItemRepository.findByCategory(category.getId())));

        return mapToDtos(items);
    }

    public List<InventoryDto> getItemsByType(String typeName) {
        List<Type> types = typeRepository.findByNameStartsWith(typeName);
        List<InventoryItem> items = new ArrayList<>();

        types.forEach(type -> items.addAll(inventoryItemRepository.findByType(type.getId())));
        return mapToDtos(items);
    }

    public List<InventoryDto> getItemsByBrand(String brand) {
        return mapToDtos(inventoryItemRepository.findByBrandStartsWith(brand));
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

    private List<InventoryDto> mapToDtos(Optional<List<InventoryItem>> inventory) {
        return mapToDtos(inventory.get()); //TODO: read up on how to handle optional.ispresent == false
    }

    private List<InventoryDto> mapToDtos(List<InventoryItem> inventory) {
        //NOTE: I dunno I do not love this, but I do not hate it. But this might be me mapping my dinosaur technology solutions to the new age
        return inventory.stream()
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
}