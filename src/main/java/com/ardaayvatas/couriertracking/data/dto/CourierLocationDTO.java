package com.ardaayvatas.couriertracking.data.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CourierLocationDTO {
    private CourierDTO courierDTO;
    private Double lat;
    private Double lng;
}
