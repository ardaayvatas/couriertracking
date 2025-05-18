package com.ardaayvatas.couriertracking.enumeration;

public enum ExceptionType {
    SERVICE_UNAVAILABLE(1L, "An unexpected error occurred."),
    UNKNOWN_ERROR(2L, "An unexpected error occurred.");

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
