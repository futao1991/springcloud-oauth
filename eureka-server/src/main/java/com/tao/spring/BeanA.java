package com.tao.spring;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BeanA {

    @Autowired
    private BeanB beanB;

    public void test() {
        System.out.println("test");
    }
}
