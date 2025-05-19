package com.ardaayvatas.couriertracking.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class ServiceLoggingAspect {

    private final LogService logService;

    @AfterReturning(pointcut = "execution(* com.ardaayvatas.couriertracking.service..*(..)) || execution(* com.ardaayvatas.couriertracking.event..*(..))",
                    returning = "result")
    public void logAfterSuccessfulCall(JoinPoint joinPoint, Object result) throws JsonProcessingException {
        String methodName = joinPoint.getSignature().getName();
        String serviceName = joinPoint.getSignature().getDeclaringTypeName();
        Object request = extractArguments(joinPoint.getArgs());
        logService.writeLog(methodName, serviceName, request, result);
    }

    @AfterThrowing(pointcut = "execution(* com.ardaayvatas.couriertracking.service..*(..)) || execution(* com.ardaayvatas.couriertracking.event..*(..))",
                   throwing = "ex")
    public void logAfterExceptionThrown(JoinPoint joinPoint, Throwable ex) throws JsonProcessingException {
        String methodName = joinPoint.getSignature().getName();
        String serviceName = joinPoint.getSignature().getDeclaringTypeName();
        Object request = extractArguments(joinPoint.getArgs());
        logService.writeErrorLog(methodName, serviceName, request, ex);
    }

    private Object extractArguments(Object[] args) {
        if (args == null || args.length == 0) {
            return null;
        }
        if (args.length == 1) {
            return args[0];
        }

        return args;
    }
}

