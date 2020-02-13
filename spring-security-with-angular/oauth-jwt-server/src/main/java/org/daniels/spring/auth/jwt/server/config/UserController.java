package org.daniels.spring.auth.jwt.server.config;


import org.daniels.spring.auth.jwt.server.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/me")
    public Principal user(Principal principal) {
        return principal;
    }

    @RequestMapping("/simple")
    public User findUser() {
        return new User().id(1L).name("john").surname("kowalski");
    }

}
