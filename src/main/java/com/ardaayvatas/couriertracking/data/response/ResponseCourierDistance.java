package com.ardaayvatas.couriertracking.data.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseCourierDistance {
    private Long courierId;
    private double totalDistance;
    private Integer storeEntranceCount;
}
