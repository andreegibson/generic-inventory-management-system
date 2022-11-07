package com.allianceair.gims.service;

import com.allianceair.gims.model.Type;
import com.allianceair.gims.repository.TypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TypeService {
    private final TypeRepository typeRepository;

    public List<Type> findAllTypes() {
        return typeRepository.findAll();
    }

    public Type addType(Type type) {
        return typeRepository.save(type);
    }
}
