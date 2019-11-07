package org.daniels.spring.didemo.controllers;

import org.daniels.spring.didemo.services.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class SetterInjectedController {
    public String sayHello(){
        return greetingService.sayGreeting();
    }

    private GreetingService greetingService;

    @Autowired
    @Qualifier("getterGreetingService")
    public void setGreetingService(GreetingService greetingService) {
        this.greetingService = greetingService;
    }
}
