package com.allianceair.gims.repository;

import com.allianceair.gims.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, Integer> {
}
