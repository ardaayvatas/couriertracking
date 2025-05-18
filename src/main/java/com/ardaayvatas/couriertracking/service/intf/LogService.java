package com.ardaayvatas.couriertracking.service.intf;

public interface LogService {

    void writeLog(String methodName, String serviceName, String request, String response);

    void writeErrorLog(String methodName, String serviceName, String request, Throwable ex);
}
