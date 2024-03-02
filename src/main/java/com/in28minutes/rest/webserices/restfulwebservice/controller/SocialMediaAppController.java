package com.in28minutes.rest.webserices.restfulwebservice.controller;

import com.in28minutes.rest.webserices.restfulwebservice.dao.UserDAOService;
import com.in28minutes.rest.webserices.restfulwebservice.entity.User;
import com.in28minutes.rest.webserices.restfulwebservice.exception.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
public class SocialMediaAppController {

    private final UserDAOService userDAOService;

    public SocialMediaAppController(UserDAOService userDAOService) {
        this.userDAOService = userDAOService;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = userDAOService.saveUser(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable Integer id) {
        User fetchedUser = userDAOService.findUser(id);
        if (fetchedUser == null)
            throw new UserNotFoundException("No User can be found with Id: " + id);
        return fetchedUser;
    }

    @GetMapping("/users")
    public List<User> retrieveUsers() {
        return userDAOService.getAll();
    }

    @DeleteMapping("/users/{id}")
    public boolean deleteUserById(@PathVariable Integer id) {
        boolean userRemoved = userDAOService.deleteUser(id);

        return userRemoved;
    }

}
