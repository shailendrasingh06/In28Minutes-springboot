package com.in28minutes.rest.webserices.restfulwebservice.repo;

import com.in28minutes.rest.webserices.restfulwebservice.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostJPARepository extends JpaRepository<Post, Integer> {
}
