package com.re.hackathon.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j

// áp dụng aop để kiểm soát log. hệ thống tự log ra console thông tin về method-name khi có luồng dữ liệu gọi vào chức năng thêm,cập nhật
public class LoggingAspect {
    @Pointcut("execution(* com.re.hackathon.controller.*.*(..))")
    public void controllerMethods() {}
    @Pointcut("execution(* com.re.hackathon.service.*.*(..))")
    public void serviceMethods() {}
    @Before("controllerMethods()")
    public void logMethodName(JoinPoint joinPoint) {
        log.info("Method called: {}", joinPoint.getSignature().getName());
    }


    @AfterReturning(pointcut = "serviceMethods()", returning = "result")
    public void logServiceResult(JoinPoint joinPoint, Object result) {
        if (result != null) {
            log.info("Method {} returned: {}", joinPoint.getSignature().getName(), result.toString());
        } else {
            log.info("Method {} returned: null", joinPoint.getSignature().getName());
        }
    }


    @Around("controllerMethods()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long elapsed = System.currentTimeMillis() - start;
        log.info("Method {} executed in {} ms", joinPoint.getSignature().getName(), elapsed);
        return result;
    }
}
