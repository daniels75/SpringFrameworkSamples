package org.daniels.spring.tutorial.securitydemo.resource;

import org.daniels.spring.tutorial.securitydemo.model.Todo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CustomerResource.BASE_URL)
public class CustomerResource {
    public static final String BASE_URL = "/api";

    @RequestMapping("/customer")
    public Todo customerTodo() {
        return new Todo().id(3L).title("Customer title").description("Customer description");
    }

}
