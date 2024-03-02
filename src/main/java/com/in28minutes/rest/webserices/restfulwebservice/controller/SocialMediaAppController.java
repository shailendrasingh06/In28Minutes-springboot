package com.in28minutes.rest.webserices.restfulwebservice.controller;

import com.in28minutes.rest.webserices.restfulwebservice.dao.UserDAOService;
import com.in28minutes.rest.webserices.restfulwebservice.entity.User;
import com.in28minutes.rest.webserices.restfulwebservice.exception.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.swing.text.html.parser.Entity;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


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
    public EntityModel<User> retrieveUser(@PathVariable Integer id) {

        User fetchedUser = userDAOService.findUser(id);
        if (fetchedUser == null)
            throw new UserNotFoundException("No User can be found with Id: " + id);

        EntityModel<User> userEntityModel = EntityModel.of(fetchedUser);

        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveUsers());

        userEntityModel.add(link.withRel("all-users"));

        return userEntityModel;
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
