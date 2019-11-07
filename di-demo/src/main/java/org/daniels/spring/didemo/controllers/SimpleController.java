package org.daniels.spring.didemo.controllers;

import org.daniels.spring.didemo.services.GreetingService;
import org.springframework.stereotype.Controller;

@Controller
public class SimpleController {

    private GreetingService greetingService;

    public SimpleController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public String hello() {
        System.out.println("Hello");
        return this.greetingService.sayGreeting();
    }
}
