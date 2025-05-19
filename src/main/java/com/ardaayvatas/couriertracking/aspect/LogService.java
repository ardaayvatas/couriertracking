package com.ardaayvatas.couriertracking.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface LogService {

    void writeLog(String methodName, String serviceName, Object request, Object response) throws JsonProcessingException;

    void writeErrorLog(String methodName, String serviceName, Object request, Throwable ex) throws JsonProcessingException;
}
