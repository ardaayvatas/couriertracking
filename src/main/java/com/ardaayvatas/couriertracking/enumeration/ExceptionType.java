package com.ardaayvatas.couriertracking.enumeration;

import lombok.Getter;

@Getter
public enum ExceptionType {
    UNKNOWN_ERROR(44444L, "An unexpected error occurred."),
    EVENT_ERROR(11111L, "An exception occurred during the event."),
    COURIER_NOT_FOUND(123L, "Courier not found." ),
    COURIER_DISTANCE_ERROR(124L, "Courier distance not found.");

    private final Long code;
    private final String message;

    ExceptionType(Long code, String message) {
        this.code = code;
        this.message = message;
    }

}
