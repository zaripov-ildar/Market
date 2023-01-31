package ru.geekbrains.Market.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
//@Aspect
//@Component
public class AllMethodsDebugger {
    @Pointcut("execution (* ru.geekbrains.Market..*(..) ) && !execution (* ru.geekbrains.Market.configs.JwtRequestFilter.*(..)) ")
    public void allMethods(){}
    @Around("allMethods()")
    public Object aroundAllMethods(ProceedingJoinPoint pjp) throws Throwable {
        String methodName = pjp.getSignature().getName();
        Object[] args = pjp.getArgs();
        log.debug("{}({})", methodName, Arrays.toString(args));
        return pjp.proceed();
    }
}
