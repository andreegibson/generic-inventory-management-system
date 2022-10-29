package com.allianceair.gims.repository;

import com.allianceair.gims.model.Type;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TypeRepository extends MongoRepository<Type, Integer> {
}
