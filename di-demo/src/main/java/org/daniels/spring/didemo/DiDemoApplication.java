package org.daniels.spring.didemo;

import org.daniels.spring.didemo.controllers.SimpleController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DiDemoApplication {

    public static void main(String[] args) {
        final ApplicationContext appContext = SpringApplication.run(DiDemoApplication.class, args);
        SimpleController simpleController  = (SimpleController)appContext.getBean("simpleController");
        simpleController.hello();
    }

}
