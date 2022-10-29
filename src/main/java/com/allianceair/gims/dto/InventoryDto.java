package com.allianceair.gims.dto;

import com.allianceair.gims.model.ServiceOrder;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@Builder
public class InventoryDto {
    @Id
    private String id;
    private String name;
    private String category;
    private String type;
    private String brand;
    private String description;
    private String imageUrl;
    private List<ServiceOrder> serviceOrders;
}
