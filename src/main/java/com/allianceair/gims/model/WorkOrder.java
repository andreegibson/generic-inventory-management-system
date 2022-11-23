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
@Document(collection = "workOrder")
public class WorkOrder {

    @Id
    private String id;
    private String customerFirstName;
    private String customerLastName;
    private String customerAddress;
    private String customerCity;
    private String customerState;
    private String customerPostalCode;
    private String workRequestDescription;
    private LocalDate completedDate;
    private LocalDateTime dateAdded;
    private List<String> inventoryIds;
}
