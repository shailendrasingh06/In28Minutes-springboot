package com.in28minutes.rest.webserices.restfulwebservice.dao;

import com.in28minutes.rest.webserices.restfulwebservice.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

@Component
public class UserDAOService {

    private static int userId = 1;

    private static List<User> userList = new ArrayList<>();

    static {
        userList.add(new User(userId++, "Manom", LocalDate.of(1997, 12, 16)));
        userList.add(new User(userId++, "Shailendra", LocalDate.of(1998, 12, 16)));
        userList.add(new User(userId++, "Ujala", LocalDate.of(2000, 12, 16)));
    }

    public User saveUser(User user) {
        user.setId(userId++);
        userList.add(user);
        return user;
    }

    public List<User> getAll() {
        return userList;
    }

    public boolean deleteUser(User user) {
        return userList.remove(user);
    }

    public User findUser(Integer id) {
        return userList.stream().filter(user -> Objects.equals(user.getId(), id)).findAny().orElse(null);
    }

    public boolean deleteUser(Integer id) {

        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return userList.removeIf(predicate);
    }

}
