package com.june.common;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class AnHook {

    @Before("execution(* com.june.service.UserServiceImpl.* (..))")

    public void before(){
        System.out.println("====注解实现-执行之前=======");
    }
}
