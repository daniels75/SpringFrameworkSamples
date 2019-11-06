package org.daniels.spring.didemo.controllers;

import org.daniels.spring.didemo.services.GreetingService;
import org.daniels.spring.didemo.services.GreetingServiceImpl;

public class PropertyInjectedController {

    public GreetingServiceImpl greetingService;

    String sayHello() {
        return greetingService.sayGreeting();
    }

}
