package com.demo.test;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ClassC {
    @Bean
    public void Hello(){
        System.out.println("hello C");
    }
}
