package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User(1,"Adams", LocalDate.now().minusYears(30)));
        users.add(new User(2,"Eve", LocalDate.now().minusYears(10)));
        users.add(new User(3,"Jack", LocalDate.now().minusYears(40)));

    }

    public List<User> allUsers() {
        return users;
    }

    public User user(int id) {
//        for (User user: users) {
//            if (user.getId() == id) {
//                return user;
//            }
//        }
//        return null;
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().get();
    }
}
