package com.allianceair.gims.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "types")
public class Type {

    @Id
    private String Id;
    private Boolean consumable;
}
