package com.allianceair.gims.service;

import com.allianceair.gims.model.WorkOrder;
import com.allianceair.gims.repository.WorkOrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class WorkOrderService {
    private final WorkOrderRepository workOrderRepository;
    private final ItemService itemService;

    public List<WorkOrder> findOpenWorkOrders() {
        return workOrderRepository.findOpenWorkOrders();
    }

    public WorkOrder addWorkOrder(WorkOrder workOrder) {
        workOrder.setDateAdded(LocalDateTime.now());

        return workOrderRepository.save(workOrder);
    }

    public Optional<WorkOrder> findById(String id) {
        return workOrderRepository.findById(id);
    }

    public WorkOrder updateWorkOrder(WorkOrder workOrder) {
        workOrder.setLastModified(LocalDateTime.now());

        List<String> existingInventoryIds = workOrderRepository.findById(workOrder.getId()).get().getInventoryIds();

        chargeOffInventory(workOrder.getInventoryIds(), existingInventoryIds);

        return workOrderRepository.save(workOrder);
    }

    private void chargeOffInventory(List<String> incomingInventoryIds, List<String> existingInventoryIds) {
        log.debug("Charging off Inventory");

        if(existingInventoryIds == null)
            log.trace("Charging off all inventory");

        incomingInventoryIds.forEach(inventoryId -> {
            if(existingInventoryIds == null || !existingInventoryIds.contains(inventoryId)){
                log.trace("Charing of Inventory {}", inventoryId);
                itemService.chargeOffItem(inventoryId, "sold");
            }
        });
    }
}

