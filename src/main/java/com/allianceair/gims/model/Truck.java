package com.allianceair.gims.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Document(collection = "trucks")
public class Truck {

    @Id
    private String id;
    private String number;
    private String driver;
    private String dateOccupied;
    private String description;
    private List<TruckItem> inventory;
}
