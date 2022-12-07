package com.allianceair.gims.repository;

import com.allianceair.gims.model.Truck;
import com.allianceair.gims.model.WorkOrder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TruckRepository extends MongoRepository<Truck, String> {
    public List<Truck> findAll();
    public Truck findTruckByNumberIs(String number);
}
