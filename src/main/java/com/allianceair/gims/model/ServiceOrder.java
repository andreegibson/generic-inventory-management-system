package com.allianceair.gims.model;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ServiceOrder {
    private LocalDateTime serviceDateTime;
    private String serviceDescription;
    private LocalDateTime serviceCompletedDate;
}
