package com.ardaayvatas.couriertracking.service;

import com.ardaayvatas.couriertracking.data.dao.model.ErrorLog;
import com.ardaayvatas.couriertracking.data.dao.model.ServiceLog;
import com.ardaayvatas.couriertracking.data.dao.repository.ErrorLogRepository;
import com.ardaayvatas.couriertracking.data.dao.repository.ServiceLogRepository;
import com.ardaayvatas.couriertracking.service.intf.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {

    private final ServiceLogRepository serviceLogRepository;
    private final ErrorLogRepository errorLogRepository;

    private static final int MAX_LOG_LENGTH = 4000;

    @Override
    @Async
    public void writeLog(String methodName, String serviceName, String request, String response) {
        ServiceLog log = new ServiceLog();
        log.setLogDate(java.time.LocalDateTime.now());
        log.setMethodName(methodName);
        log.setServiceName(serviceName);
        log.setRequest(trim(request));
        log.setResponse(trim(response));
        serviceLogRepository.save(log);
    }

    @Override
    @Async
    public void writeErrorLog(String methodName, String serviceName, String request, Throwable ex) {
        String errorDetail = ex.toString() + "\n" + getStackTrace(ex);
        ErrorLog log = new ErrorLog();
        log.setErrorDate(java.time.LocalDateTime.now());
        log.setMethodName(methodName);
        log.setServiceName(serviceName);
        log.setRequest(trim(request));
        log.setErrorDetail(trim(errorDetail));
        errorLogRepository.save(log);
    }

    private String getStackTrace(Throwable ex) {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement e : ex.getStackTrace()) {
            sb.append(e).append("\n");
        }
        return sb.toString();
    }

    private String trim(String text) {
        if (text == null) return null;
        return text.length() > MAX_LOG_LENGTH ? text.substring(0, MAX_LOG_LENGTH) : text;
    }

}

