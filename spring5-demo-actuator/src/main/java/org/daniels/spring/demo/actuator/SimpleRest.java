package org.daniels.spring.demo.actuator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sample")
public class SimpleRest {

    @Autowired
    private Environment env;

    @GetMapping
    public String info() {
        return "sample";
    }
}
