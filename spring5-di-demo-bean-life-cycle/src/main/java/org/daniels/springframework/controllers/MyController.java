package org.daniels.springframework.controllers;

import org.daniels.springframework.services.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


@Controller
public class MyController {

    @Autowired
    SimpleBean simpleBean;
    @Autowired
    private GreetingService greetingService;

    public String hello(){
        System.out.println("Hello!!! ");

        return greetingService.sayGreeting();
    }

    public SimpleBean getSimpleBean() {
        return simpleBean;
    }

    public GreetingService getGreetingService() {
        return greetingService;
    }


}
