package com.ardaayvatas.couriertracking.aspect;

import com.ardaayvatas.couriertracking.service.intf.LogService;
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
    public void logAfterSuccessfulCall(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        String serviceName = joinPoint.getSignature().getDeclaringTypeName();
        String request = extractArguments(joinPoint.getArgs());
        String response = result != null ? result.toString() : null;

        logService.writeLog(methodName, serviceName, request, response);
    }

    @AfterThrowing(pointcut = "execution(* com.ardaayvatas.couriertracking.service..*(..)) || execution(* com.ardaayvatas.couriertracking.event..*(..))",
                   throwing = "ex")
    public void logAfterExceptionThrown(JoinPoint joinPoint, Throwable ex) {
        String methodName = joinPoint.getSignature().getName();
        String serviceName = joinPoint.getSignature().getDeclaringTypeName();
        String request = extractArguments(joinPoint.getArgs());

        logService.writeErrorLog(methodName, serviceName, request, ex);
    }

    private String extractArguments(Object[] args) {
        if (args == null || args.length == 0) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        for (Object arg : args) {
            sb.append(arg != null ? arg.toString() : "null").append("; ");
        }

        return sb.toString();
    }
}

