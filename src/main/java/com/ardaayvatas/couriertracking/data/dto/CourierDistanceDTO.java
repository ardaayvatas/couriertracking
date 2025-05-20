package com.ardaayvatas.couriertracking.data.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CourierDistanceDTO {
    private Long id;
    private CourierDTO courierDTO;
    private double totalDistance;
    private Integer storeEntranceCount;
}
