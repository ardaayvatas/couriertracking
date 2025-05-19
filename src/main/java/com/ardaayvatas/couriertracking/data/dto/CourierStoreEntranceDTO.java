package com.ardaayvatas.couriertracking.data.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class CourierStoreEntranceDTO {
    private Long id;
    private CourierDTO courierDTO;
    private StoreDTO storeDTO;
    private LocalDateTime createdDate;
}
