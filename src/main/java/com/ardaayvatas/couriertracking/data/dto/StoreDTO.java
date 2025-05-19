package com.ardaayvatas.couriertracking.data.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StoreDTO {
    private Long id;
    private String name;
    private Double lat;
    private Double lng;
}
