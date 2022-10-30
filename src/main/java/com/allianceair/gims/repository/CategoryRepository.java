package com.allianceair.gims.repository;

import com.allianceair.gims.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CategoryRepository extends MongoRepository<Category, Integer> {
    List<Category> findByNameStartsWithIgnoreCase(String categoryName);
}
