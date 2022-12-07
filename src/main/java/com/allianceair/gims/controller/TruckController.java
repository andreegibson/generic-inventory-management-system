package com.allianceair.gims.controller;

import com.allianceair.gims.model.Truck;
import com.allianceair.gims.service.TruckService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
//TODO - Make this better :)
@CrossOrigin("http://localhost:3000")
@RequestMapping("/trucks")
@Slf4j
public class TruckController {
    private final TruckService truckService;

    @GetMapping
    public List<Truck> findAllTrucks() {
        return truckService.findAllTrucks();
    }

    @GetMapping("/{number}")
    public Truck findTruckWithNumber(@PathVariable String number) {
        return truckService.findTruckByNumber(number);
    }

//    @PostMapping
//    @ResponseStatus(code = HttpStatus.CREATED)
//    public WorkOrder addWorkOrder(@RequestBody WorkOrder workOrder) {
//        return truckService.addWorkOrder(workOrder);
//    }
//
//    @GetMapping("/{id}")
//    public Optional<WorkOrder> findWorkOrder(@PathVariable String id) {
//        return truckService.findById(id);
//    }
//
//    @PutMapping
//    public WorkOrder updateWorkOrder(@RequestBody WorkOrder workOrder) {
//        log.info("Updating Work Order {}", workOrder.getId());
//
//        return truckService.updateWorkOrder(workOrder);
//    }
}