package com.allianceair.gims.model;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
public class ServiceOrder {
    private Date serviceDateTime;
    private String serviceDescription;
    private Date serviceCompletedDate;
    private LocalDateTime created;
    private LocalDateTime lastModifed;
}
