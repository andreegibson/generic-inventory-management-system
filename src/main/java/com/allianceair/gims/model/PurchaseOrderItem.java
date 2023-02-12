package com.allianceair.gims.model;

import lombok.Builder;
import lombok.Data;

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
}
