package com.allianceair.gims.service;

import com.allianceair.gims.model.WorkOrder;
import com.allianceair.gims.repository.WorkOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@AllArgsConstructor
public class WorkOrderService {
    private final WorkOrderRepository workOrderRepository;

    public List<WorkOrder> findOpenWorkOrders() {
        return workOrderRepository.findOpenWorkOrders();
    }

    public WorkOrder addWorkOrder(WorkOrder workOrder) {
        workOrder.setDateAdded(LocalDateTime.now());

        return workOrderRepository.save(workOrder);
    }
}
