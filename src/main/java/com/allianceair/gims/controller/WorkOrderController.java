package com.allianceair.gims.controller;

import com.allianceair.gims.model.WorkOrder;
import com.allianceair.gims.service.WorkOrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
//TODO - Make this better :)
@CrossOrigin("http://localhost:3000")
@RequestMapping("/workorders")
@Slf4j
public class WorkOrderController {
    private final WorkOrderService workOrderService;

    @GetMapping
    public List<WorkOrder> findOpenWorkOrders() {
        return workOrderService.findOpenWorkOrders();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public WorkOrder addWorkOrder(@RequestBody WorkOrder workOrder) {
        return workOrderService.addWorkOrder(workOrder);
    }

    @GetMapping("/{id}")
    public Optional<WorkOrder> findWorkOrder(@PathVariable String id) {
        return workOrderService.findById(id);
    }

    @PutMapping
    public WorkOrder updateWorkOrder(@RequestBody WorkOrder workOrder) {
        log.info("Updating Work Order {}", workOrder.getId());

        return workOrderService.updateWorkOrder(workOrder);
    }
}