package org.geeksforgeeks.digitallibrary.beans;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Before("execution(* org.geeksforgeeks.gfg_spring_project.*.*.*(..))")
    public void logMethodCall(JoinPoint joinPoint) {
        log.info("Logging: Calling {} with arguments: {}",
                joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
    }

}

