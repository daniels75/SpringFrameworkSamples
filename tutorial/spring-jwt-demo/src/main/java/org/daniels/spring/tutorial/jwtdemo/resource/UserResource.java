package org.daniels.spring.tutorial.jwtdemo.resource;

import com.google.common.collect.Lists;
import org.daniels.spring.tutorial.jwtdemo.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserResource {

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public User getSimpleEmployee() {
        return new User().id(1L).name("Dan");
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<User> getEmployess() {
        return Lists.newArrayList(new User().id(1L).name("Dan"), new User().id(2L).name("Jan"));
    }
}
