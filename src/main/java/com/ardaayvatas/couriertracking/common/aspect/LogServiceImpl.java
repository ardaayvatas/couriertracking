package com.ardaayvatas.couriertracking.common.aspect;

import com.ardaayvatas.couriertracking.data.dao.model.ErrorLog;
import com.ardaayvatas.couriertracking.data.dao.model.ServiceLog;
import com.ardaayvatas.couriertracking.data.dao.repository.ErrorLogRepository;
import com.ardaayvatas.couriertracking.data.dao.repository.ServiceLogRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {

    private final ServiceLogRepository serviceLogRepository;
    private final ErrorLogRepository errorLogRepository;
    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    private static final int MAX_LOG_LENGTH = 4000;

    @Override
    @Async
    public void writeLog(String methodName, String serviceName, Object request, Object response) throws JsonProcessingException {
        ServiceLog log = new ServiceLog();
        log.setLogDate(java.time.LocalDateTime.now());
        log.setMethodName(methodName);
        log.setServiceName(serviceName);
        log.setRequest(trim(toJson(request)));
        log.setResponse(trim(toJson(response)));
        serviceLogRepository.save(log);
    }

    @Override
    @Async
    public void writeErrorLog(String methodName, String serviceName, Object request, Throwable ex) throws JsonProcessingException {
        String errorDetail = ex.toString() + "\n" + getStackTrace(ex);
        ErrorLog log = new ErrorLog();
        log.setErrorDate(java.time.LocalDateTime.now());
        log.setMethodName(methodName);
        log.setServiceName(serviceName);
        log.setRequest(trim(toJson(request)));
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

    private String toJson(Object object) throws JsonProcessingException {
        if (object == null) return null;
        else {
            return objectMapper.writeValueAsString(object);
        }
    }
}

