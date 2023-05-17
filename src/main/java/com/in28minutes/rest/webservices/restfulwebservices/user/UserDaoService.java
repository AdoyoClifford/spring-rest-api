package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();
    public static int userCount = 0;

    static {
        users.add(new User(++userCount,"Adams", LocalDate.now().minusYears(30)));
        users.add(new User(++userCount,"Eve", LocalDate.now().minusYears(10)));
        users.add(new User(++userCount,"Jack", LocalDate.now().minusYears(40)));

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
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public User saveUser(User user) {
        user.setId(++userCount);
        users.add(user);
        return user;
    }
}
