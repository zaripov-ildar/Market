package ru.geekbrains.Market.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
//@Aspect
//@Component
public class TimeService {

    @Pointcut("@annotation(ru.geekbrains.Market.aop.Timer)")
    public void methodTimer() {
    }

    @Pointcut("@within(ru.geekbrains.Market.aop.Timer)")
    public void classTimer() {
    }

    @Around("methodTimer()")
    public Object markMethodTime(ProceedingJoinPoint pjp) throws Throwable {
        String methodName = "Method " + pjp.getSignature().getName();
        return markTime(methodName, pjp);
    }

    @Around("classTimer()")
    public Object markClassTime(ProceedingJoinPoint pjp) throws Throwable {
        String className = "Class " + pjp.getTarget().getClass().getSimpleName();
        return markTime(className, pjp);
    }

    private Object markTime(String name, ProceedingJoinPoint pjp) throws Throwable {
        try {
            long startTime = System.currentTimeMillis();
            Object proceed = pjp.proceed();
            Long finishTime = System.currentTimeMillis() - startTime;
            log.debug("{} time working is {} ms", name, finishTime);
            return proceed;
        } catch (Exception e) {
            String methodName = pjp.getSignature().getName();
            String className = pjp.getClass().getName();
            log.error("Method {} from class {} threw exception {}", methodName, className, e.getMessage());
            return null;
        }
    }
}
