package org.daniels.spring.didemo.controllers;

import org.springframework.stereotype.Controller;

@Controller
public class SimpleController {

    public String hello() {
        System.out.println("Hello");
        return "Hello - simple controller";
    }
}
