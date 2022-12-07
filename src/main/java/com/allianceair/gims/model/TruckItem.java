package com.allianceair.gims.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class TruckItem {

    private String item;
    private String category;
    private String type;
    private String brand;
    private int quantity;
}
