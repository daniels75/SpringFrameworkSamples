package org.daniels.springboot.samples.initalization;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class PostConstructBeanExample {

    @PostConstruct
    public void init() {
        System.out.println(getClass().getSimpleName() + " has been initialized.");
    }

    public String info() {
        return "Simple message from: " + getClass().getSimpleName();
    }
}
