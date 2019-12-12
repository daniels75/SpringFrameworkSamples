package org.daniels.spring.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class WebController {

/*
    @RequestMapping({"/", "/index", "index.html", "/home", "home.html"})
     public String home() {
       return "index";
   }
*/

/*    @RequestMapping({"/", "/ui"})
    public RedirectView ui() {
        return new RedirectView("index.html");
    }*/

    @GetMapping({"/", "/ui"})
    public ModelAndView forwardToUI(ModelMap model) {
        return new ModelAndView("forward:/ui/index.html", model);
    }
}
