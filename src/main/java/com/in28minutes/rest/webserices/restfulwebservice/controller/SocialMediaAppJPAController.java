package com.in28minutes.rest.webserices.restfulwebservice.controller;

import com.in28minutes.rest.webserices.restfulwebservice.entity.Post;
import com.in28minutes.rest.webserices.restfulwebservice.entity.User;
import com.in28minutes.rest.webserices.restfulwebservice.exception.UserNotFoundException;
import com.in28minutes.rest.webserices.restfulwebservice.repo.UserRepository;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
public class SocialMediaAppJPAController {

    UserRepository repository;

    public SocialMediaAppJPAController(UserRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = repository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable Integer id) {

        Optional<User> fetchedUser = repository.findById(id);
        if (fetchedUser.isEmpty())
            throw new UserNotFoundException("No User can be found with Id: " + id);

        EntityModel<User> userEntityModel = EntityModel.of(fetchedUser.get());

        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveUsers());

        userEntityModel.add(link.withRel("all-users"));

        return userEntityModel;
    }


    @GetMapping("/jpa/users")
    public List<User> retrieveUsers() {

        return repository.findAll();
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUserById(@PathVariable Integer id) {
         repository.deleteById(id);
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retreiveUserPosts(@PathVariable Integer id) {
        Optional<User> user = repository.findById(id);

        if(user.isEmpty())
            throw new UserNotFoundException("User not found for ID: " + id);

        return user.get().getPosts();
    }

}
