package com.ardaayvatas.couriertracking.data.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CourierDTO {
    private Long id;
    private String name;
    private String surname;
}
