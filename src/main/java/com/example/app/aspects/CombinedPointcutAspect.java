package com.example.app.aspects;

import com.example.app.service.UserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Aspect
public class CombinedPointcutAspect {
    private final Logger log = LoggerFactory.getLogger(CombinedPointcutAspect.class);

    @Pointcut("execution(* com.example.app.service.*.*(..))")
    public void serviceLayer() {}

    @Before("serviceLayer()")
    public void serviceLayerBefore(JoinPoint joinPoint) {
        log.info("======BEFORE======");
    }

    @Pointcut("execution(* get*(..))")
    public void getters() {}

    @Pointcut("execution(* save*(..)) || execution(* update*(..))")
    public void modifyingMethods() {}

    @After("modifyingMethods()")
    public void modifyingMethodsAfter(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        List<String> collect = Arrays.stream(args).map(Object::toString).collect(Collectors.toList());
        log.info("======AFTER with args====== " + collect);
    }

    @Pointcut("serviceLayer() && getters()")
    public void serviceGetters() {}

    //@Before("execution(* com.example.service.UserService.*(..))")
    //@Before("execution(com.example.model.User com.example.service.*.*(..))")
    //@Before("execution(* com.example.service.*.*(Long, ..))")
    //@Before("execution(private * com.example.service.*.*(..))")
    //@Pointcut("@annotation(com.example.annotation.Timed)")
    //@Pointcut("execution(* com.example.service.*.*(..) throws IllegalArgumentException)")

    @Around("serviceGetters()")
    public Object serviceAround(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("======AROUND======");
        Object result = joinPoint.proceed();
        return result;
    }

    @AfterThrowing(pointcut = "serviceGetters()", throwing = "ex")
    public void serviceAfterThrowing(JoinPoint joinPoint, Exception ex) {
        log.error(ex.getMessage());
    }

    @AfterReturning(pointcut = "modifyingMethods()", returning = "result")
    public void serviceAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("======AFTER RETURNING======");
    }
}
