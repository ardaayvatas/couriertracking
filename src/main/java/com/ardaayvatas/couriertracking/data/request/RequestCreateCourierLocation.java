package com.ardaayvatas.couriertracking.data.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestCreateCourierLocation {
    @NotNull
    private Long courierId;
    @NotNull
    private Double lat;
    @NotNull
    private Double lng;
}
