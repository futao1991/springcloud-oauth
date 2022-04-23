package com.tao.spring;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserAspect {

    @Pointcut("execution(* com.tao.spring.*.*(..))")
    public void addLog() {}

    @Before("addLog()")
    public void before() {
        System.out.println("before");
    }
}
