package com.allianceair.gims.service;

import com.allianceair.gims.model.Truck;
import com.allianceair.gims.repository.TruckRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class TruckService {
    private final TruckRepository truckRepository;

    public List<Truck> findAllTrucks() {
        return truckRepository.findAll();
    }
    public Truck findTruckByNumber(String number) {
        return truckRepository.findTruckByNumberIs(number);
    }
}

