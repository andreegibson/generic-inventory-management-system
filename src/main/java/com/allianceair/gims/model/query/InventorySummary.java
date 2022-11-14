package com.allianceair.gims.model.query;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class InventorySummary {

    @Id
    private String type;
    private long countInventory;
}
