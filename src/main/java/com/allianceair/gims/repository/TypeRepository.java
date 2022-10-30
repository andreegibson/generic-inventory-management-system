package com.allianceair.gims.repository;

import com.allianceair.gims.model.Type;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TypeRepository extends MongoRepository<Type, Integer> {
    List<Type> findByNameStartsWith(String type);
}
