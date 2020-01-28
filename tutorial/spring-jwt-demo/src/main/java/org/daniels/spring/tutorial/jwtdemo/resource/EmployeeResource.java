package org.daniels.spring.tutorial.jwtdemo.resource;

import com.google.common.collect.Lists;
import org.daniels.spring.tutorial.jwtdemo.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeResource {

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Employee getSimpleEmployee() {
        return new Employee().id(1L).name("Dan");
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getEmployess() {
        return Lists.newArrayList(new Employee().id(1L).name("Dan"), new Employee().id(1L).name("Jan"));
    }
}
