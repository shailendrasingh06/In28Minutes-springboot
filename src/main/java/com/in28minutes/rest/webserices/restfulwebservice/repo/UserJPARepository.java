package com.in28minutes.rest.webserices.restfulwebservice.repo;

import com.in28minutes.rest.webserices.restfulwebservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJPARepository extends JpaRepository<User, Integer> {
}
