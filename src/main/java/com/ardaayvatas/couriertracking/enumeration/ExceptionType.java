package com.ardaayvatas.couriertracking.enumeration;

public enum ExceptionType {
    UNKNOWN_ERROR(2L, "An unexpected error occurred."),
    EVENT_ERROR(11111L, "An exception occurred during the event."),
    COURIER_NOT_FOUND(123L, "Courier not found." );

    private final Long code;
    private final String message;

    ExceptionType(Long code, String message) {
        this.code = code;
        this.message = message;
    }

    public Long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
