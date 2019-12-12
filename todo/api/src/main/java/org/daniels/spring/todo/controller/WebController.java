package org.daniels.spring.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

    @RequestMapping({"/", "/index", "index.html", "/home", "home.html"})
    public String home() {
        return "index";
    }

    @RequestMapping({"/ui"})
    public String ui() {
        return "index";
    }
}
