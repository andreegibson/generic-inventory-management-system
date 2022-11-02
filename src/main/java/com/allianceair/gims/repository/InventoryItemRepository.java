package com.allianceair.gims.repository;

import com.allianceair.gims.model.InventoryItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InventoryItemRepository extends MongoRepository<InventoryItem, String> {
    List<InventoryItem> findByNameStartsWithIgnoreCase(String name);
    List<InventoryItem> findByCategoryStartsWithIgnoreCase(String category);
    List<InventoryItem> findByTypeStartsWithIgnoreCase(String type);
    List<InventoryItem> findByBrandStartsWithIgnoreCase(String brand);

    @Query("{ $and : [ { name: '#name' } , { category : '#category' } ] }")
    List<InventoryItem> findAllByNameAndCategoryStartsWithIgnoreCase(@Param("name") String name, @Param("category") String category);
}
