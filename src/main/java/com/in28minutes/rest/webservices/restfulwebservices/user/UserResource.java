package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserResource {
    UserDaoService service;

    @Autowired
    public UserResource(UserDaoService service) {
        this.service = service;
    }
    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.allUsers();
    }
    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id) {
        return service.user(id);
    }
}
