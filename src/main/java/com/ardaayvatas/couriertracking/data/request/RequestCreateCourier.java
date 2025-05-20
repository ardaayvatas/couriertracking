package com.ardaayvatas.couriertracking.data.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestCreateCourier {
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
}
