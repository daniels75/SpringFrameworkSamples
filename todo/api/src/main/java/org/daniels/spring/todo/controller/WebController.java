package org.daniels.spring.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class WebController {

    @GetMapping({"/", "/ui"})
    public ModelAndView forwardToUI(ModelMap model) {
        return new ModelAndView("forward:/index.html", model);
    }

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
//  please keep in mind to modify a frontend package.json
//  "build": "ng build --base-href /ui/ --output-path dist/resources/static/ui"

/*    @GetMapping({"/", "/ui"})
    public ModelAndView forwardToUI(ModelMap model) {
        return new ModelAndView("forward:/ui/index.html", model);
    }*/
}
