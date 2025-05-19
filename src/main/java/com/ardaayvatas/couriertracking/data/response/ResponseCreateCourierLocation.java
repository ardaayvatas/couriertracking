package com.ardaayvatas.couriertracking.data.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseCreateCourierLocation {
    private Long courierId;
    private Double lat;
    private Double lng;
}
