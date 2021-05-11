package com.allianceair.gims.repository;

import com.allianceair.gims.model.InventoryItem;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryItemRepository extends MongoRepository<InventoryItem, String> {
    Optional<InventoryItem> findByName(String name);
    List<InventoryItem> findByCategory(String category);
    List<InventoryItem> findByType(String type);
    List<InventoryItem> findByBrand(String brand);
}
