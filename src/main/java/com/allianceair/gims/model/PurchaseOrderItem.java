package com.allianceair.gims.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PurchaseOrderItem {
    private String name;
    private String category;
    private String type;
    private String brand;
    private String description;
    private String imageUrl;
    private Integer quantity;
    private LocalDateTime dateAdded;
    private LocalDateTime lastModified;
    private Integer quantityReceived;
}
