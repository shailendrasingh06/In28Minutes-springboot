package com.in28minutes.rest.webserices.restfulwebservice.controller;

import com.in28minutes.rest.webserices.restfulwebservice.entity.Post;
import com.in28minutes.rest.webserices.restfulwebservice.entity.User;
import com.in28minutes.rest.webserices.restfulwebservice.exception.UserNotFoundException;
import com.in28minutes.rest.webserices.restfulwebservice.repo.PostJPARepository;
import com.in28minutes.rest.webserices.restfulwebservice.repo.UserJPARepository;
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

    UserJPARepository userJPARepository;

    PostJPARepository postJPARepository;

    public SocialMediaAppJPAController(UserJPARepository repository, PostJPARepository postJPARepository) {
        this.userJPARepository = repository;
        this.postJPARepository = postJPARepository;
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = userJPARepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable Integer id) {

        Optional<User> fetchedUser = userJPARepository.findById(id);
        if (fetchedUser.isEmpty())
            throw new UserNotFoundException("No User can be found with Id: " + id);

        EntityModel<User> userEntityModel = EntityModel.of(fetchedUser.get());

        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveUsers());

        userEntityModel.add(link.withRel("all-users"));

        return userEntityModel;
    }


    @GetMapping("/jpa/users")
    public List<User> retrieveUsers() {

        return userJPARepository.findAll();
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUserById(@PathVariable Integer id) {
         userJPARepository.deleteById(id);
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retreiveUserPosts(@PathVariable Integer id) {
        Optional<User> user = userJPARepository.findById(id);

        if(user.isEmpty())
            throw new UserNotFoundException("User not found for ID: " + id);

        return user.get().getPosts();
    }

    @PostMapping("jpa/users/{id}/posts")
    public ResponseEntity<Post> createPostForUser(@PathVariable Integer id, @Valid @RequestBody Post post) {
        Optional<User> user = userJPARepository.findById(id);

        if(user.isEmpty())
            throw new UserNotFoundException("User not found for ID: " + id);

        post.setUser(user.get());

        Post savedPost = postJPARepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(savedPost)
                .toUri();
        return ResponseEntity.created(location).build();
    }

}
