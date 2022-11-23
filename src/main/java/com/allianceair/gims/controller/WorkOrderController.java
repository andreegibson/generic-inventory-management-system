package com.allianceair.gims.controller;

import com.allianceair.gims.model.WorkOrder;
import com.allianceair.gims.service.WorkOrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
//TODO - Make this better :)
@CrossOrigin("http://localhost:3000")
@RequestMapping("/workorders")
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
}