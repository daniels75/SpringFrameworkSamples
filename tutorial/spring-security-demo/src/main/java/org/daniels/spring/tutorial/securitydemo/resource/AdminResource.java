package org.daniels.spring.tutorial.securitydemo.resource;

import com.google.common.collect.Lists;
import org.daniels.spring.tutorial.securitydemo.model.Admin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api")
public class AdminResource {

    @GetMapping("/admin/{id}")
    public Admin getAdmin(@PathVariable Long id) {
        final Admin fakeAdmin = new Admin();
        return fakeAdmin.id(id).title("Admin").description("Admin account");
    }

    @GetMapping("/admin")
    public List<Admin> retrieveAll() {
        final Admin admin1 = new Admin().id(1L)
                .title("First Admin").description("admin desc1");

        final Admin admin2 = new Admin().id(2L)
                .title("Second Acmin").description("admin desc2");

        return Lists.newArrayList(admin1, admin2);
    }
}
