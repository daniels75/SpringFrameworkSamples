package org.daniels.springboot.samples.initalization;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
