package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;
import ru.yandex.practicum.filmorate.storage.InMemoryUserStorage;
import ru.yandex.practicum.filmorate.controller.UserController;

import java.time.LocalDate;

@SpringBootTest
public class UserTest {
    private User user;
    private User user1;
    private User user2;
    private UserController userController;

    @BeforeEach
    void beforeEach() {
        userController = new UserController(new UserService(new InMemoryUserStorage()));
        user = new User(1, "post@email.com",
                "Login", LocalDate.of(2000, 10, 11), "Name");

        user1 = new User(2, "post2@email.com",
                "Login2", LocalDate.of(2020, 10, 11), "Name0");

        user2 = new User(3, "post2@email.com",
                "Login2", LocalDate.of(2017, 10, 11), "Name.");
    }

    @Test
    public void createTest() {
        userController.createUser(user);
        Assertions.assertEquals(1, userController.getAllUsers().size());
    }

    @Test
    public void updateTest() {
        userController.createUser(user);
        user.setId(12);
        Assertions.assertEquals(12, user.getId());
    }

    @Test
    public void getAllTest() {
        userController.createUser(user);
        userController.createUser(user1);
        Assertions.assertEquals(2, userController.getAllUsers().size());
    }

    @Test
    public void getByIdTest() {
        userController.createUser(user);
        userController.createUser(user1);
        Assertions.assertEquals(user1, userController.getUserById(2));
    }

    @Test
    public void addFriends() {
        userController.createUser(user);
        userController.createUser(user1);
        userController.addFriend(user.getId(), user1.getId());
        Assertions.assertEquals(1, user.getFriends().size());
        userController.getFriend(user.getId());
        Assertions.assertEquals(user1.getId(), 2);
    }
}