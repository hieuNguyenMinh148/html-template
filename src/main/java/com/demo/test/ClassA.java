package com.demo.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClassA {
    @Autowired
    private ClassB b;
    public void getHello(){
        b.getHello();
    }

}
