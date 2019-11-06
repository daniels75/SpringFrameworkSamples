package org.daniels.spring.didemo.services;

import org.springframework.stereotype.Service;

@Service
public class GreetingServiceImpl implements GreetingService{
    public static final String GREETING = "Hello from service";

    @Override
    public String sayGreeting() {
        return GREETING;
    }
}
