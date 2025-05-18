package com.ardaayvatas.couriertracking.common.exception;

import com.ardaayvatas.couriertracking.enumeration.ExceptionType;

public class ServiceCallException extends RuntimeException {

    private final ExceptionType exceptionType;

    public ServiceCallException(ExceptionType exceptionType) {
        super(exceptionType.getMessage());
        this.exceptionType = exceptionType;
    }

    public ServiceCallException(ExceptionType exceptionType, Throwable cause) {
        super(exceptionType.getMessage(), cause);
        this.exceptionType = exceptionType;
    }

    public ExceptionType getExceptionType() {
        return exceptionType;
    }
}
