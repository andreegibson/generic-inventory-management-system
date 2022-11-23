package com.allianceair.gims.repository;

import com.allianceair.gims.model.WorkOrder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface WorkOrderRepository extends MongoRepository<WorkOrder, String> {
    @Query("{ \"workOrders.completedDate\": { $exists: false }}")
    public List<WorkOrder> findOpenWorkOrders();
}
