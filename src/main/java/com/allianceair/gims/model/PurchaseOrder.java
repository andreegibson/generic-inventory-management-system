package com.allianceair.gims.model;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Document(collection = "purchaseOrder")
public class PurchaseOrder {

    @Id
    private String id;
    private String vendor;
    private PurchaseOrderStatus status;
    private List<PurchaseOrderItem> items;
    private LocalDateTime dateAdded;
    private LocalDateTime lastModified;
}
